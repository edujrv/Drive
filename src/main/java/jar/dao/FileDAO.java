package jar.dao;

import java.io.FileInputStream;
//Imports
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import jar.DriveConnection;
import jar.model.dto.FileDTO;
import jar.model.dto.FolderDTO;
import javafx.util.Pair;

public class FileDAO {

	public static jar.model.File getFile(FileDTO file) throws IOException {
		File aux = DriveConnection.service.files().get(file.getIdElement())
				.setFields("parents, size, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners")
				.execute();

		return FileUtils.parseFile(aux, file);
	}

	public static jar.model.Folder getFolder(FolderDTO folder) throws IOException {
		File aux = DriveConnection.service.files().get(folder.getIdElement()).setFields(
				"parents, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, folderColorRgb")
				.execute();
		return FileUtils.parseFolder(aux, folder);
	}

	public static jar.model.File getFile(String id) throws IOException {
		File file = DriveConnection.service.files().get(id).setFields(
				"id, name, parents, size, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared")
				.execute();
		return FileUtils.parseFile(file);
	}

	public static jar.model.Folder getFolder(String id) throws IOException {
		File folder = DriveConnection.service.files().get(id).setFields(
				"id, name, parents, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser, folderColorRgb")
				.execute();
		return FileUtils.parseFolder(folder);
	}

	public static PageTokenStep newQuery() {
		return new QueryBuilder();
	}

	/**
	 * 1st step. The page token is set here.
	 */
	public interface PageTokenStep {
		PageSizeStep setPageToken(String pageToken) throws IOException;

		PageSizeStep startFromBeginning() throws IOException;
	}

	/**
	 * 2nd step. The page size is set here.
	 */
	public interface PageSizeStep {
		TypeStep setPageSize(int pageSize);

		TypeStep defaultPageSize();
	}

	/**
	 * 3rd step. The type of file to be return is set here.
	 */
	public interface TypeStep {
		WhereFileStep getFiles();

		WhereFolderStep getFolders();
	}

	/**
	 * 4th step. Where the files/folders should be fetched from This step also
	 * applies to files.
	 */
	public interface WhereFolderStep {
		SharedStep fromFolder(String folderId);

		SharedStep fromMyDrive();

		SharedStep fromAnywhere();

		SharedStep fromStarred();

		Build fromTrashed();

		OrderStep fromShared();
	}

	/**
	 * 4th step only for files. Files can also fetch from the recent tab.
	 */
	public interface WhereFileStep extends WhereFolderStep {
		SharedBuildStep fromRecent();
	}

	/**
	 * 5th step. Filters based on the ownership (who ownes them) of the
	 * files/folders.
	 */
	public interface SharedStep {
		OrderStep myOwnershipOnly();

		OrderStep anyOwnership();
	}

	/**
	 * 5th and final step only for files from "fromRecent". This is because files
	 * that come from "fromRecent" are already ordered.
	 */
	public interface SharedBuildStep {
		Build myFilesOnly();

		Build anyFiles();
	}

	/**
	 * 6th step. Orders (or not) the files/folders.
	 */
	public interface OrderStep {
		Build notOrdered();

		Build orderBySize();

		Build setOrder(String orderBy);
	}

	/**
	 * 7th step and final step. Builds the object.
	 */
	public interface Build {
		Pair<String, List<Object>> build() throws IOException;
	}

	private static class QueryBuilder implements PageTokenStep, PageSizeStep, TypeStep, WhereFileStep, SharedStep,
			SharedBuildStep, OrderStep, Build {
		private Drive.Files.List fileList;
		private String pageToken = null;
		private String folderId = "root";
		private int pageSize = 10;
		private String query = "";
		private String fields = "nextPageToken, files(id, name, mimeType, shared, starred)";
		// private String fields = "nextPageToken, files(id, name, parents, size, kind,
		// mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe,
		// viewedByMeTime, owners, shared, sharingUser)";
		private String orderBy = "";
		private boolean isFile = true;

		// ## Build
		@Override
		public Pair<String, List<Object>> build() throws IOException {
			FileList result = fileList.execute();
			this.pageToken = result.getNextPageToken();

			List<Object> objs = new ArrayList<Object>();
			if (isFile)
				for (File file : result.getFiles())
					objs.add((Object) FileUtils.parseFileDTO(file));
			else
				for (File file : result.getFiles())
					objs.add((Object) FileUtils.parseFolderDTO(file));

			return new Pair<String, List<Object>>(pageToken, objs);
		}

		// ## PageTokenStep
		@Override
		public PageSizeStep setPageToken(String pageToken) throws IOException {
			this.pageToken = pageToken;
			this.fileList = DriveConnection.service.files().list().setPageToken(pageToken).setFields(this.fields)
					.set("corpora", "user");
			return this;
		}

		@Override
		public PageSizeStep startFromBeginning() throws IOException {
			this.fileList = DriveConnection.service.files().list().setPageToken(this.pageToken).setFields(this.fields)
					.set("corpora", "user");
			return this;
		}

		// ## PageSizeStep
		@Override
		public TypeStep setPageSize(int pageSize) {
			this.pageSize = pageSize;
			this.fileList.setPageSize(pageSize);
			return this;
		}

		@Override
		public TypeStep defaultPageSize() {
			this.fileList.setPageSize(this.pageSize);
			return this;
		}

		// ## TypeStep
		@Override
		public WhereFileStep getFiles() {
			query = "not mimeType contains 'vnd.google-apps.folder'";
			return this;
		}

		@Override
		public WhereFolderStep getFolders() {
			isFile = false;
			query = "mimeType contains 'vnd.google-apps.folder'";
			fields = fields.substring(0, fields.length() - 1) + ", folderColorRgb)";
			return this;
		}

		// ## WhereFolderStep && WhereFilesStep
		@Override
		public SharedStep fromFolder(String folderId) {
			this.folderId = folderId;
			this.query = this.query.concat(" and '" + folderId + "' in parents");
			return this;
		}

		@Override
		public SharedStep fromMyDrive() {
			this.query = this.query.concat(" and '" + this.folderId + "' in parents and not trashed");
			return this;
		}

		@Override
		public SharedStep fromStarred() {
			this.query = this.query.concat(" and starred");
			return this;
		}

		@Override
		public SharedStep fromAnywhere() {
			return this;
		}

		@Override
		public Build fromTrashed() {
			this.query = this.query.concat(" and trashed");
			this.fileList.setQ(this.query);
			return this;
		}

		// ## WhereFilesStep
		@Override
		public SharedBuildStep fromRecent() {
			this.orderBy = "viewedByMeTime desc";
			this.fileList.set("orderBy", this.orderBy);
			return this;
		}

		// ## SharedStep
		@Override
		public OrderStep myOwnershipOnly() {
			this.query = this.query.concat(" and 'me' in owners");
			this.fileList.setQ(this.query);
			return this;
		}

		@Override
		public OrderStep anyOwnership() {
			this.fileList.setQ(this.query);
			return this;
		}

		@Override
		public OrderStep fromShared() {
			this.query = this.query.concat(" and sharedWithMe");
			this.fileList.setQ(this.query);
			return this;
		}

		// ## SharedBuildStep
		@Override
		public Build myFilesOnly() {
			this.query = this.query.concat(" and 'me' in owners");
			this.fileList.setQ(this.query);
			return this;
		}

		@Override
		public Build anyFiles() {
			this.fileList.setQ(this.query);
			return this;
		}

		// ## OrderStep
		@Override
		public Build notOrdered() {
			return this;
		}

		@Override
		public Build setOrder(String orderBy) {
			this.orderBy = orderBy;
			this.fileList.set("orderBy", orderBy);
			return this;
		}

		@Override
		public Build orderBySize() {
			this.fileList.set("orderBy", "quotaBytesUsed");
			return this;
		}

	}

	// TODO: Fix the download path and the type of files that you can download
	// TODO: Check the right position of this function
	public static void downloadFile(FileDTO file) {
		try {
			jar.model.File aux = FileDAO.getFile(file);
			String fileId = aux.getIdElement();

			FileOutputStream outputStream = new FileOutputStream("downloads/" + aux.getName());

			DriveConnection.service.files().get(fileId)
					.executeMediaAndDownloadTo(outputStream);

			outputStream.close();

			System.out.println("Se descargo " + aux.getName() + "!!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createFolder(String name, String folderId) {
		try {
			File fileMetadata = new File();
			fileMetadata.setName(name);
			fileMetadata.setMimeType("application/vnd.google-apps.folder");
			fileMetadata.setParents(Collections.singletonList(folderId));

			File file = DriveConnection.service.files().create(fileMetadata)
					.setFields("id")
					.execute();
			System.out.println("Folder ID: " + file.getId());

			// createFile("archivoPrueba", file.getName(), file.getId());
			// uploadFile("pruebaa", file.getId());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createFile(String name, String path, String folderID) {
		try {
			String folderId = folderID;
			File fileMetadata = new File();
			fileMetadata.setName(name);
			fileMetadata.setMimeType("application/vnd.google-apps.spreadsheet");
			// path -> "application/vnd.google-apps."
			// excel -> "spreadsheet"
			// doc -> "document"
			// pdf -> "pdf"
			// jam -> "jam"
			// powergoogle -> "presentation"
			// jpeg | jpg -> "image/jpeg"
			// mp4 -> "video/mp4"
			// txt -> "text/plain"

			fileMetadata.setParents(Collections.singletonList(folderId));

			File file = DriveConnection.service.files().create(fileMetadata)
					.setFields("id, parents")
					.execute();
			System.out.println("File ID: " + file.getId());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void uploadFile(String name, String folderID) {
		try {
			String folderId = folderID;
			File fileMetadata = new File();
			fileMetadata.setName(name);

			fileMetadata.setParents(Collections.singletonList(folderId));
			java.io.File filePath = new java.io.File("upload/prueba.txt");
			// ## Upload an especific file
			FileContent mediaContent = new FileContent("image/jpeg", filePath);
			File file = DriveConnection.service.files().create(fileMetadata, mediaContent)
					.setFields("id, parents")
					.execute();
			System.out.println("File ID: " + file.getId());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

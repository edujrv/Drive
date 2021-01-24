package jar.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import jar.DriveConnection;
import javafx.util.Pair;

public class FileDAO {
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

		SharedStep fromStarred();

		Build fromTrashed();
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
		public Build fromTrashed() {
			this.query = this.query.concat(" and trahsed");
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

	}

}

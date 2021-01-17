package jar.dao;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.User;

import jar.DriveConnection;
import jar.model.Content;
import jar.model.ContentType;
import jar.model.ElementDataCreate;
import jar.model.ElementLastOpened;
import jar.model.ElementLastUpdate;
import jar.model.Folder;
import javafx.util.Pair;

public class FileDAO {

	public static PageTokenStep newQuery() {
		return new QueryBuilder();
	}

	public interface PageTokenStep {
		PageSizeStep setPageToken(String pageToken) throws IOException;

		PageSizeStep startFromBeginning() throws IOException;
	}

	public interface PageSizeStep {
		TypeStep setPageSize(int pageSize);

		TypeStep defaultPageSize();
	}

	public interface TypeStep {
		WhereFileStep getFiles();

		WhereFolderStep getFolders();
	}

	public interface WhereFolderStep {
		SharedStep fromFolder(String folderId);

		SharedStep fromMyDrive();

		SharedStep fromStarred();

		Build fromTrashed();
	}

	public interface WhereFileStep extends WhereFolderStep {
		SharedBuildStep fromRecent();
	}

	public interface SharedStep {
		OrderStep myOwnershipOnly();

		OrderStep anyOwnership();
	}

	public interface SharedBuildStep {
		Build myFilesOnly();

		Build anyFiles();
	}

	public interface OrderStep {
		Build notOrdered();

		Build setOrder(String orderBy);
	}

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
		private String fields = "nextPageToken, files(id, name, parents, size, kind, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser)";
		private String orderBy = "";
		private boolean isFile;

		@Override
		public Pair<String, List<Object>> build() throws IOException {
			FileList result = fileList.execute();
			this.pageToken = result.getNextPageToken();

			List<Object> objs = new ArrayList<Object>();
			if (isFile)
				for (File file : result.getFiles())
					objs.add((Object) parseFile(file));
			else
				for (File file : result.getFiles())
					objs.add((Object) parseFolder(parseFile(file)));

			return new Pair<String, List<Object>>(pageToken, objs);
		}

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

		@Override
		public WhereFileStep getFiles() {
			this.isFile = true;
			this.query = "not mimeType contains 'vnd.google-apps.folder'";
			return this;
		}

		@Override
		public WhereFolderStep getFolders() {
			this.isFile = false;
			this.query = "mimeType contains 'vnd.google-apps.folder'";
			return this;
		}

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

		@Override
		public SharedBuildStep fromRecent() {
			this.orderBy = "viewedByMeTime desc";
			this.fileList.set("orderBy", this.orderBy);
			return this;
		}

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

	private static List<String> getPath(List<String> arr) {
		List<String> path = new ArrayList<String>();
		try {
			do {
				File f = DriveConnection.service.files().get(arr.get(0)).setFields("name, parents").execute();
				arr = f.getParents();
				path.add(0, f.getName());
			} while (arr != null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return path;
		}
		return path;
	}

	private static jar.model.User parseUser(User us) {
		jar.model.User r = new jar.model.User();
		if (us == null)
			return null;
		r.setName(us.getDisplayName());
		r.setMail(us.getEmailAddress());
		return r;
	}

	private static LocalDateTime parseDateTime(DateTime dt) {
		if (dt == null)
			return null;
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(dt.getValue()), TimeZone.getDefault().toZoneId());
	}

	private static jar.model.File parseFile(File file) {
		jar.model.File aux = new jar.model.File();

		aux.setIdElement(file.getId());
		aux.setName(file.getName());
		aux.setPath(getPath(file.getParents()));

		Content c = new Content();
		c.setContentType(getType(file.getMimeType()));

		if (c.getContentType().getType() == ContentType.TYPE.FOLDER || file.getSize() == null)
			aux.setFileSize(0);
		else
			aux.setFileSize(file.getSize());

		aux.setIsErased(false);
		aux.setIsFeatured(file.getStarred());

		c.setIsShared(file.getShared());
		c.setOwner(parseUser(file.getOwners().get(0)));
		c.setDataCreate(new ElementDataCreate(parseDateTime(file.getCreatedTime()), c.getOwner()));

		c.setLastUpdate(
				new ElementLastUpdate(parseDateTime(file.getModifiedTime()), parseUser(file.getLastModifyingUser())));
		c.setLastOpened(new ElementLastOpened(parseDateTime(file.getViewedByMeTime()), file.getViewedByMe()));

		aux.setContent(c);

		return aux;
	}

	private static Folder parseFolder(jar.model.File file) {
		Folder aux = new Folder();

		aux.setIdElement(file.getIdElement());
		aux.setName(file.getName());
		aux.setPath(getPath(file.getPath()));
		aux.setContent(file.getContent());
		aux.setIsErased(false);
		aux.setIsFeatured(file.isFeatured());

		return aux;
	}

	private static ContentType getType(String mimeType) {
		String left = mimeType.substring(0, mimeType.indexOf('/'));
		switch (left) {
			case "application":
				String right = mimeType.substring(mimeType.indexOf('/') + 1, mimeType.length());
				return new ContentType(getApplicationType(right));
			case "image":
				return new ContentType(ContentType.TYPE.IMAGE);
			case "text":
				return new ContentType(ContentType.TYPE.TEXT);
			case "video":
				return new ContentType(ContentType.TYPE.VIDEO);
			case "audio":
				return new ContentType(ContentType.TYPE.AUDIO);
			default:
				return new ContentType(ContentType.TYPE.UNKNOWN);
		}
	}

	private static ContentType.TYPE getApplicationType(String app) {
		// Google's stuff
		if (app.startsWith("vnd.google"))
			switch (app) {
				case "vnd.google-apps.folder":
					return ContentType.TYPE.FOLDER;

				case "vnd.google-apps.document":
				case "vnd.google-apps.drawing":
				case "vnd.google-apps.form":
				case "vnd.google-apps.presentation":
				case "vnd.google-apps.spreadsheet":
					return ContentType.TYPE.OFFICE;

				case "vnd.google-apps.audio":
					return ContentType.TYPE.AUDIO;

				case "vnd.google-apps.drive-sdk":
				case "vnd.google-apps.file":
				case "vnd.google-apps.fusiontable":
				case "vnd.google-apps.map":
				case "vnd.google-apps.shortcut":
				case "vnd.google-apps.site":
					return ContentType.TYPE.FILE;

				case "application/vnd.google-apps.photo":
					return ContentType.TYPE.IMAGE;

				case "application/vnd.google-apps.video":
					return ContentType.TYPE.VIDEO;

				case "application/vnd.google-apps.script":
					return ContentType.TYPE.TEXT;

				default:
					return ContentType.TYPE.UNKNOWN;
			}

		if (app.contains("office") || app.contains("word") || app.contains("document") || app.contains("excel")
				|| app.contains("spreadsheet") || app.contains("powerpoint") || app.contains("presentation")
				|| app.contains("write") || app.contains("rtf"))
			return ContentType.TYPE.OFFICE;

		if (app.contains("zip") || app.contains("x-7z") || app.equals("vnd.rar") || app.equals("x-tar")
				|| app.equals("gzip") || app.equals("java-archive"))
			return ContentType.TYPE.FILE;

		if (app.equals("pdf"))
			return ContentType.TYPE.PDF;

		if (app.equals("json"))
			return ContentType.TYPE.TEXT;
		return ContentType.TYPE.UNKNOWN;
	}
}

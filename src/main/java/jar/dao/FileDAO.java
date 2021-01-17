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

	public interface TypeStep {
		WhereFileStep getFiles();

		WhereFolderStep getFolders();
	}

	public interface WhereFolderStep {
		FolderStep fromMyDrive();

		SharedStep fromStarred();

		Build fromTrashed();
	}

	public interface WhereFileStep extends WhereFolderStep {
		SharedBuildStep fromRecent();
	}

	public interface FolderStep {
		SharedStep fromFolder(String folderId);

		SharedStep fromRoot();
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
		List<Object> build();
	}

	public static class Builder
			implements TypeStep, WhereFileStep, FolderStep, SharedStep, SharedBuildStep, OrderStep, Build {
		private Drive.Files.List fileList;
		private String pageToken = null;
		private String folderId = "root";
		private int pageSize = 10;
		private String query = "";
		private String fields = "nextPageToken, files(id, name, parents, size, kind, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser)";
		private String orderBy;

		public Builder(String pageToken) throws IOException {
			this.pageToken = pageToken;
			this.fileList = DriveConnection.service.files().list().setPageToken(this.pageToken);
		}

		@Override
		public List<Object> build() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public WhereFileStep getFiles() {
			this.query.concat("");
			return this;
		}

		@Override
		public WhereFolderStep getFolders() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public FolderStep fromMyDrive() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public SharedStep fromStarred() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public Build fromTrashed() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public SharedBuildStep fromRecent() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public SharedStep fromFolder(String folderId) {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public SharedStep fromRoot() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public OrderStep myOwnershipOnly() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public OrderStep anyOwnership() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public Build myFilesOnly() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public Build anyFiles() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public Build notOrdered() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public Build setOrder(String orderBy) {
			// TODO Auto-generated method stub
			return this;
		}

	}

	private List<String> getPath(List<String> arr) {
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

	private jar.model.User parseUser(User us) {
		jar.model.User r = new jar.model.User();
		if (us == null)
			return null;
		r.setName(us.getDisplayName());
		r.setMail(us.getEmailAddress());
		return r;
	}

	private LocalDateTime parseDateTime(DateTime dt) {
		if (dt == null)
			return null;
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(dt.getValue()), TimeZone.getDefault().toZoneId());
	}

	private jar.model.File parseFile(File file) {
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

	private Folder parseFolder(jar.model.File file) {
		Folder aux = new Folder();

		aux.setIdElement(file.getIdElement());
		aux.setName(file.getName());
		aux.setPath(getPath(file.getPath()));
		aux.setContent(file.getContent());
		aux.setIsErased(false);
		aux.setIsFeatured(file.isFeatured());

		return aux;
	}

	private ContentType getType(String mimeType) {
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

	private ContentType.TYPE getApplicationType(String app) {
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

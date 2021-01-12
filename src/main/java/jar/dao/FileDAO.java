package jar.dao;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.User;

import jar.DriveConnection;
import jar.model.Content;
import jar.model.ContentType;
import jar.model.ElementDataCreate;
import jar.model.ElementLastOpened;
import jar.model.ElementLastUpdate;
import javafx.util.Pair;

public class FileDAO {
	/**
	 * @param folderId  : Set to null if you want to list all files in root folder
	 * @param startOver : If you want to start paging over
	 * @return List of all files in 'folderId' paged.
	 * @throws IOException
	 */
	public Pair<String, List<jar.model.File>> getAll(String folderId, String pageToken) throws IOException {
		if (folderId == null)
			folderId = "root";

		List<jar.model.File> r = new ArrayList<jar.model.File>();
		// long ti = System.currentTimeMillis();
		FileList result = DriveConnection.service.files().list().set("corpora", "user").setPageSize(10)
				.setQ("'" + folderId + "' in parents and 'me' in owners and not trashed")
				.setFields(
						"nextPageToken, files(id, name, parents, size, kind, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser)")
				.setPageToken(pageToken).execute();

		pageToken = result.getNextPageToken();
		// System.out.println("DeltaT: " + ((System.currentTimeMillis() - ti) / 1000.0)
		// + "seg");

		for (File file : result.getFiles())
			r.add(parseFile(file));
		return new Pair<>(pageToken, r);
	}

	public Optional<jar.model.File> get(String id) {
		File file = null;
		try {
			file = DriveConnection.service.files().get(id).setFields(
					"id, name, parents, size, kind, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser")
					.execute();
		} catch (IOException e) {
			file = null;
		}
		return Optional.of(parseFile(file));
	}

	public void save(jar.model.File e) {
		// TODO Auto-generated method stub

	}

	public void update(String id, jar.model.File e) {
		// TODO Auto-generated method stub

	}

	public void delete(jar.model.File e) {
		// TODO Auto-generated method stub

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
		// TODO: Revisar que el tiempo este correcto
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

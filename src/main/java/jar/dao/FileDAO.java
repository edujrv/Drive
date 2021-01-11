package jar.dao;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.DriveList;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.User;

import jar.DriveConnection;
import jar.model.Content;
import jar.model.ContentType;
import jar.model.ElementDataCreate;
import jar.model.ElementLastOpened;
import jar.model.ElementLastUpdate;

public class FileDAO implements IDAO<jar.model.File> {

	// Para Mi Unidad
	@Override
	public List<jar.model.File> getAll(int page) {
		List<jar.model.File> r = new ArrayList<jar.model.File>();
		try {
			String pageToken = null;
			do {
//				FileList result = DriveConnection.service.files().list().setSpaces("drive").setFields(
//						"nextPageToken, files(id, name, parents, size, kind, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser)")
//						.setPageToken(pageToken).execute();

				FileList result = DriveConnection.service.files().list().setQ("'me' in owners and not trashed").setSpaces("drive").setFields(
						"nextPageToken, files(id, name, parents, size, kind, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser)")
						.setPageToken(pageToken).execute();

				pageToken = result.getNextPageToken();
				
				for (File file : result.getFiles()) {
					jar.model.File aux = new jar.model.File();

					aux.setIdElement(file.getId());
					aux.setName(file.getName());
					aux.setPath(getPath(file.getParents()));

					Content c = new Content();
					c.setContentType(getType(file.getMimeType()));
					System.out.println(file.getName() + ": " + file.getMimeType() + " => "
							+ c.getContentType().getType() + " || " + aux.getPath());

					if (c.getContentType().getType() == ContentType.TYPE.FOLDER || file.getSize() == null)
						aux.setFileSize(-1);
					else
						aux.setFileSize(file.getSize());

					aux.setIsErased(file.getTrashed());
					aux.setIsFeatured(file.getStarred());

					c.setIsShared(file.getShared());
					c.setOwner(parseUser(file.getOwners().get(0)));
					c.setDataCreate(new ElementDataCreate(parseDateTime(file.getCreatedTime()), c.getOwner()));

					c.setLastUpdate(new ElementLastUpdate(parseDateTime(file.getModifiedTime()),
							parseUser(file.getLastModifyingUser())));
					c.setLastOpened(
							new ElementLastOpened(parseDateTime(file.getViewedByMeTime()), file.getViewedByMe()));

					aux.setContent(c);
					file.getMimeType();

					r.add(aux);
				}

			} while (pageToken != null);

		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Optional<jar.model.File> get(int id) {
//		File f = DriveConnection.service.files().get("a!").setFields("").execute();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(jar.model.File e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int id, jar.model.File e) {
		// TODO Auto-generated method stub

	}

	@Override
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

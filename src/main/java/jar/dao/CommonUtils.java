package jar.dao;

//Imports
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.User;

import jar.DriveConnection;
import jar.model.ContentType;
import jar.model.Folder;

public class CommonUtils {

    static List<Folder> getPath(List<String> arr) {
        List<Folder> path = new ArrayList<Folder>();
        try {
            do {
                File f = DriveConnection.service.files().get(arr.get(0)).setFields(
                        "id, name, parents, mimeType, starred, trashed, createdTime, modifiedTime, viewedByMe, viewedByMeTime, owners, shared, sharingUser")
                        .execute();
                arr = f.getParents();
                path.add(0, FileUtils.parseFolder(f));
            } while (arr != null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return path;
        }
        return path;
    }

    static jar.model.User parseUser(User us) {
        jar.model.User r = new jar.model.User();
        if (us == null)
            return null;
        r.setName(us.getDisplayName());
        r.setMail(us.getEmailAddress());
        return r;
    }

    static LocalDateTime parseDateTime(DateTime dt) {
        if (dt == null)
            return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dt.getValue()), TimeZone.getDefault().toZoneId());
    }

    // File type (Alternatives)
    static ContentType getType(String mimeType) {
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

    static ContentType.TYPE getApplicationType(String app) {
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

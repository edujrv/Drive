package jar.dao;

import com.google.api.services.drive.model.File;

import jar.model.Content;
import jar.model.ContentType;
import jar.model.ElementDataCreate;
import jar.model.ElementLastOpened;
import jar.model.ElementLastUpdate;
import jar.model.Folder;
import jar.model.dto.ContentDTO;
import jar.model.dto.FileDTO;
import jar.model.dto.FolderDTO;

public class FileUtils {

    static FileDTO parseFileDTO(File file) {
        FileDTO aux = new jar.model.dto.FileDTO();

        aux.setIdElement(file.getId());
        aux.setName(file.getName());

        ContentDTO c = new ContentDTO();
        c.setContentType(CommonUtils.getType(file.getMimeType()));
        c.setShared(file.getShared());
        c.setStarred(file.getStarred());

        aux.setContent(c);

        return aux;
    }

    static FolderDTO parseFolderDTO(File file) {
        FolderDTO aux = new FolderDTO();

        aux.setIdElement(file.getId());
        aux.setName(file.getName());
        aux.setColor(file.getFolderColorRgb());

        ContentDTO c = new ContentDTO();
        c.setContentType(CommonUtils.getType(file.getMimeType()));
        c.setShared(file.getShared());
        c.setStarred(file.getStarred());

        aux.setContent(c);

        return aux;
    }

    static jar.model.File parseFile(File file, FileDTO dto) {
        jar.model.File aux = new jar.model.File();

        aux.setIdElement(dto.getIdElement());
        aux.setName(dto.getName());
        aux.setPath(CommonUtils.getPath(file.getParents()));

        Content c = new Content();
        ContentDTO cdto = dto.getContent();

        c.setContentType(cdto.getContentType());
        c.setErased(file.getTrashed());
        c.setShared(cdto.isShared());
        c.setStarred(cdto.isStarred());
        c.setOwner(CommonUtils.parseUser(file.getOwners().get(0)));
        c.setDataCreate(new ElementDataCreate(CommonUtils.parseDateTime(file.getCreatedTime()), c.getOwner()));
        c.setLastUpdate(new ElementLastUpdate(CommonUtils.parseDateTime(file.getModifiedTime()),
                CommonUtils.parseUser(file.getLastModifyingUser())));
        c.setLastOpened(
                new ElementLastOpened(CommonUtils.parseDateTime(file.getViewedByMeTime()), file.getViewedByMe()));

        aux.setContent(c);

        if (c.getContentType().getType() == ContentType.TYPE.FOLDER || file.getSize() == null)
            aux.setFileSize(0);
        else
            aux.setFileSize(file.getSize());

        return aux;
    }

    static Folder parseFolder(File file, FolderDTO dto) {
        Folder aux = new Folder();

        aux.setIdElement(dto.getIdElement());
        aux.setName(dto.getName());
        aux.setPath(CommonUtils.getPath(file.getParents()));
        aux.setColor(dto.getColor());

        Content c = new Content();
        ContentDTO cdto = dto.getContent();

        c.setContentType(cdto.getContentType());
        c.setErased(file.getTrashed());
        c.setShared(cdto.isShared());
        c.setStarred(cdto.isStarred());
        c.setOwner(CommonUtils.parseUser(file.getOwners().get(0)));
        c.setDataCreate(new ElementDataCreate(CommonUtils.parseDateTime(file.getCreatedTime()), c.getOwner()));
        c.setLastUpdate(new ElementLastUpdate(CommonUtils.parseDateTime(file.getModifiedTime()),
                CommonUtils.parseUser(file.getLastModifyingUser())));
        c.setLastOpened(
                new ElementLastOpened(CommonUtils.parseDateTime(file.getViewedByMeTime()), file.getViewedByMe()));

        aux.setContent(c);

        return aux;
    }

    static jar.model.File parseFile(File file) {
        jar.model.File aux = new jar.model.File();

        aux.setIdElement(file.getId());
        aux.setName(file.getName());
        aux.setPath(CommonUtils.getPath(file.getParents()));

        Content c = new Content();
        c.setContentType(CommonUtils.getType(file.getMimeType()));

        if (c.getContentType().getType() == ContentType.TYPE.FOLDER || file.getSize() == null)
            aux.setFileSize(0);
        else
            aux.setFileSize(file.getSize());

        c.setErased(file.getTrashed());
        c.setStarred(file.getStarred());
        c.setShared(file.getShared());
        c.setOwner(CommonUtils.parseUser(file.getOwners().get(0)));
        c.setDataCreate(new ElementDataCreate(CommonUtils.parseDateTime(file.getCreatedTime()), c.getOwner()));
        c.setLastUpdate(new ElementLastUpdate(CommonUtils.parseDateTime(file.getModifiedTime()),
                CommonUtils.parseUser(file.getLastModifyingUser())));
        c.setLastOpened(
                new ElementLastOpened(CommonUtils.parseDateTime(file.getViewedByMeTime()), file.getViewedByMe()));

        aux.setContent(c);

        return aux;
    }

    static Folder parseFolder(File file) {
        Folder aux = new Folder();

        aux.setIdElement(file.getId());
        aux.setName(file.getName());
        aux.setPath(CommonUtils.getPath(file.getParents()));
        aux.setColor(file.getFolderColorRgb());

        Content c = new Content();
        c.setContentType(CommonUtils.getType(file.getMimeType()));

        c.setErased(file.getTrashed());
        c.setStarred(file.getStarred());
        c.setShared(file.getShared());
        c.setOwner(CommonUtils.parseUser(file.getOwners().get(0)));
        c.setDataCreate(new ElementDataCreate(CommonUtils.parseDateTime(file.getCreatedTime()), c.getOwner()));
        c.setLastUpdate(new ElementLastUpdate(CommonUtils.parseDateTime(file.getModifiedTime()),
                CommonUtils.parseUser(file.getLastModifyingUser())));
        c.setLastOpened(
                new ElementLastOpened(CommonUtils.parseDateTime(file.getViewedByMeTime()), file.getViewedByMe()));

        aux.setContent(c);

        return aux;
    }
}
package jar.model.dto;

import jar.model.ContentType;

public class ContentDTO {
    private ContentType contentType;
    private boolean shared = false;
    private boolean starred = false;

    public ContentDTO() {
    }

    public ContentDTO(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return ContentType return the contentType
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return boolean return the shared
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * @param shared the shared to set
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * @return boolean return the starred
     */
    public boolean isStarred() {
        return starred;
    }

    /**
     * @param starred the starred to set
     */
    public void setStarred(boolean starred) {
        this.starred = starred;
    }

}

package jar.model;

public class Content {

    private ContentType contentType;
    private ElementDataCreate dataCreate;
    private ElementLastUpdate lastUpdate;
    private ElementLastOpened lastOpened;
    private User owner;
    private boolean shared = false;
    private boolean starred = false;
    private boolean erased = false;

    public Content() {
    }

    public Content(ContentType contentType, ElementDataCreate dataCreate, ElementLastOpened lastOpened,
            ElementLastUpdate lastUpdate, User owner) {
        setContentType(contentType);
        setDataCreate(dataCreate);
        setLastOpened(lastOpened);
        setLastUpdate(lastUpdate);
        setOwner(owner);
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
     * @return ElementDataCreate return the dataCreate
     */
    public ElementDataCreate getDataCreate() {
        return dataCreate;
    }

    /**
     * @param dataCreate the dataCreate to set
     */
    public void setDataCreate(ElementDataCreate dataCreate) {
        this.dataCreate = dataCreate;
    }

    /**
     * @return ElementLastUpdate return the lastUpdate
     */
    public ElementLastUpdate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(ElementLastUpdate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return ElementLastOpened return the lastOpened
     */
    public ElementLastOpened getLastOpened() {
        return lastOpened;
    }

    /**
     * @param lastOpened the lastOpened to set
     */
    public void setLastOpened(ElementLastOpened lastOpened) {
        this.lastOpened = lastOpened;
    }

    /**
     * @return User return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
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
     * @return boolean return the featured
     */
    public boolean isStarred() {
        return starred;
    }

    /**
     * @param featured the featured to set
     */
    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    /**
     * @return boolean return the erased
     */
    public boolean isErased() {
        return erased;
    }

    /**
     * @param erased the erased to set
     */
    public void setErased(boolean erased) {
        this.erased = erased;
    }

}
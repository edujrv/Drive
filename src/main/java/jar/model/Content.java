package jar.model;

//import com.sun.prism.Texture;

public class Content {

    // public Texture textureContent;
    private ContentType contentType;
    private ElementDataCreate dataCreate;
    private ElementLastUpdate lastUpdate;
    private ElementLastOpened lastOpened;
    private User owner;
    private boolean isShared;
    // private List<User> sharedWith;

    /*
     * Construct.
     */

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

    public Content(ContentType contentType, ElementDataCreate dataCreate, ElementLastOpened lastOpened,
            ElementLastUpdate lastUpdate, User owner, boolean isShared) {
        this(contentType, dataCreate, lastOpened, lastUpdate, owner);
        setIsShared(isShared);
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
     * @return boolean return the isShared
     */
    public boolean isIsShared() {
        return isShared;
    }

    /**
     * @param isShared the isShared to set
     */
    public void setIsShared(boolean isShared) {
        this.isShared = isShared;
    }

}

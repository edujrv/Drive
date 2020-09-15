package jar.model;

//import com.sun.prism.Texture;

public class Content {

    // public Texture textureContent;
    private ContentType contentType;
    private ElementDataCreate dataCreate;
    private ElementLastUpdate lastUpdate;
    private ElementLastOpened lastOpened;

    /*
     * Construct.
     */

    public Content() {
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

}

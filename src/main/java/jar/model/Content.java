package jar.model;

//import com.sun.prism.Texture;

public class Content extends Element {

    // public Texture textureContent;
    public ContentType contentType;
    public ElementDataCreate DataCreate;

    /*
     * Construct.
     */

    public Content() {
    }

    /*
     * Getters.
     */

    /*
     * public Texture getTextureContent() { return textureContent; }
     */

    public ContentType getContentType() {
        return contentType;
    }

    public ElementDataCreate getDataCreate() {
        return DataCreate;
    }

    /*
     * Setters.
     */

    /*
     * public void setTextureContent(Texture textureContent) { this.textureContent =
     * textureContent; }
     */

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setDataCreate(ElementDataCreate dataCreate) {
        DataCreate = dataCreate;
    }

}

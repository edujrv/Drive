package jar.model;

public class ContentType {

    // TODO: Completar
    public static enum TYPE {
        FOLDER, IMAGE, PDF, VIDEO
    }

    private TYPE type;

    /*
     * Construct.
     */

    public ContentType() {
    }

    public ContentType(TYPE type) {
        setType(type);
    }

    /*
     * Getters.
     */

    public TYPE getType() {
        return type;
    }

    /*
     * Setters.
     */

    public void setType(TYPE type) {
        this.type = type;
    }
}

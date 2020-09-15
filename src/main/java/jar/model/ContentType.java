package jar.model;

public class ContentType extends Content {

    public String type = "";

    /*
     * Construct.
     */

    public ContentType() {
    }

    /*
     * Getters.
     */

    public String getType() {
        return type;
    }

    /*
     * Setters.
     */

    public void setType(String type) {
        this.type = type;
    }
}

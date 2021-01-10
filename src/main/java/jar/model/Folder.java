package jar.model;

public class Folder extends Element {

    private int accumulated = 0;

    /*
     * Construct.
     */

    public Folder() {
    }

    public Folder(String name, String path, Content content, Folder parentFolder, int accumulated) {
        super(name, path, content);
        setAccumulated(accumulated);
    }

    /*
     * Getters.
     */

    public int getAccumulated() {
        return accumulated;
    }

    /*
     * Setters.
     */

    public void setAccumulated(int accumulated) {
        this.accumulated = accumulated;
    }

}

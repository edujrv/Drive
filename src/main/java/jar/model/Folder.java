package jar.model;

public class Folder extends Element {

    private int accumulated = 0;

    /*
     * Construct.
     */

    public Folder() {
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

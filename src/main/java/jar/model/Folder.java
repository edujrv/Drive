package jar.model;

import java.util.List;

public class Folder extends Element {
    private String color = "black";

    public Folder() {
    }

    public Folder(String name, List<Folder> path, Content content, String color) {
        super(name, path, content);
    }

    /**
     * @return String return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    // TODO: El color que entra esta en RGB, pasar a un formato onda "black", "red"
    public void setColor(String color) {
        this.color = color;
    }
}

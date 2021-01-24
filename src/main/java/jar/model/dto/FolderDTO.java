package jar.model.dto;

public class FolderDTO extends ElementDTO {
    private String color = "black";

    public FolderDTO() {
    }

    public FolderDTO(String idElement, String name, ContentDTO content) {
        super(idElement, name, content);
    }

    public FolderDTO(String idElement, String name, ContentDTO content, String color) {
        super(idElement, name, content);
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

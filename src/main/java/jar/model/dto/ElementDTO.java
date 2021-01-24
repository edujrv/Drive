package jar.model.dto;

public abstract class ElementDTO {
    private String idElement;
    private String name;
    private ContentDTO content;

    public ElementDTO() {
    }

    public ElementDTO(String idElement, String name, ContentDTO content) {
        setIdElement(idElement);
        setName(name);
        setContent(content);
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContentDTO getContent() {
        return content;
    }

    public void setContent(ContentDTO content) {
        this.content = content;
    }

    /**
     * An element is equals to other element if their IdElement match
     */
    @Override
    public boolean equals(Object obj) {
        return getIdElement().equals(((ElementDTO) obj).getIdElement());
    }

}

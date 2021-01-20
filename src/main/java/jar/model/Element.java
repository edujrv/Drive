package jar.model;

import java.util.List;

public abstract class Element {

    private String name;
    private List<Folder> path;
    private Content content;
    private boolean isFeatured = false;
    private boolean isErased = false;
    private String idElement;

    public Element() {
    }

    public Element(String name, List<Folder> path, Content content) {
        setName(name);
        setPath(path);
        setContent(content);
    }

    /*
     * Getters.
     */

    public String getName() {
        return name;
    }

    public List<Folder> getPath() {
        return path;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public boolean isErased() {
        return isErased;
    }

    public String getIdElement() {
        return idElement;
    }

    public Content getContent() {
        return content;
    }

    /*
     * Setters.
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(List<Folder> path) {
        this.path = path;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public void setIsErased(boolean isErased) {
        this.isErased = isErased;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * An element is equals to other element if their IdElement match
     */
    @Override
    public boolean equals(Object obj) {
        return getIdElement().equals(((Element) obj).getIdElement());
    }

}

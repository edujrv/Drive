package jar.model;

import java.util.Date;

public class Element {

    public String name = "";
    public String path = "";
    public Date updatedDate;
    public boolean featured = false;
    public boolean erased = false;
    public Folder containerFolder;
    public int idElement = 0;

    /*
     * Construct.
     */
    public Element() {
    }

    /*
     * Getters.
     */

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public boolean isFeatured() {
        return featured;
    }

    public boolean isErased() {
        return erased;
    }

    public Folder getContainerFolder() {
        return containerFolder;
    }

    public int getIdElement() {
        return idElement;
    }

    /*
     * Setters.
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public void setErased(boolean erased) {
        this.erased = erased;
    }

    public void setContainerFolder(Folder containerFolder) {
        this.containerFolder = containerFolder;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }
}

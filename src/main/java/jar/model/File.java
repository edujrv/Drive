package jar.model;

public class File extends Element {
    private double size;

    public File() {
    }

    public File(String name, String path, Content content, Folder parentFolder, double size) {
        super(name, path, content, parentFolder);
        setSize(size);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

}

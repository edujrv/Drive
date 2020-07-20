package jar.model;

public class File {
    private int id;
    private String location;
    // Para carpetas el mimeType es "directory"
    private String mimeType;
    private double size;
    private String name;

    // Getters
    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getMimeType() {
        return mimeType;
    }

    public double getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setLocation(String location) {
        this.location = location;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Constructores
    public File(int id, String location, String mimeType, double size, String name) {
        this.id = id;
        setLocation(location);
        setMimeType(mimeType);
        setSize(size);
        setName(name);
    }

}
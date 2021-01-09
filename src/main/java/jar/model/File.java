package jar.model;

public class File extends Element implements Comparable<File> {
    // fileSize is in bytes
    private long fileSize;

    public File() {
    }

    public File(String name, String path, Content content, Folder parentFolder, long fileSize) {
        super(name, path, content, parentFolder);
        setFileSize(fileSize);
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * A file is comparable to other file based on their fileSize
     */
    @Override
    public int compareTo(File arg0) {
        return (int) (getFileSize() - arg0.getFileSize());
    }

}

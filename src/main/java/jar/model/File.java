package jar.model;

import java.util.List;

public class File extends Element implements Comparable<File> {
    // fileSize is in bytes
    private long fileSize;

    public File() {
    }

    public File(String name, List<Folder> path, Content content, long fileSize) {
        super(name, path, content);
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

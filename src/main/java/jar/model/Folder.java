package jar.model;

import java.util.List;

public class Folder extends Element {
	public Folder() {
	}

    public Folder(String name, List<String> path, Content content) {
        super(name, path, content);
    }
}

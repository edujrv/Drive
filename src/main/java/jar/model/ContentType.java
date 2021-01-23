package jar.model;

public class ContentType {

	public static enum TYPE {
		UNKNOWN, FOLDER, FILE, IMAGE, PDF, VIDEO, OFFICE, AUDIO, TEXT
	}

	private TYPE type;

	/*
	 * Construct.
	 */

	public ContentType() {
	}

	public ContentType(TYPE type) {
		setType(type);
	}

	/*
	 * Getters.
	 */

	public TYPE getType() {
		return type;
	}

	/*
	 * Setters.
	 */

	public void setType(TYPE type) {
		this.type = type;
	}
}

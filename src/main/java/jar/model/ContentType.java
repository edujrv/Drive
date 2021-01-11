package jar.model;

public class ContentType {

	// TODO: Completar

	// UNKNOWN: application/vnd.google-apps.unknown
	// FOLDER: application/vnd.google-apps.folder
	// FILE: application/vnd.google-apps.file
	// IMAGE: application/vnd.google-apps.photo
	// PDF

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

package media.databases;

public class MediaDatabase {
	
	private static final DatabaseService music = new MusicService();
	private static final DatabaseService images = new ImageService();
	private static final DatabaseService styles = new StyleService();
	private static final MediaDatabase instance = new MediaDatabase();

	protected MediaDatabase() {
		
	}
	
	public static MediaDatabase getInstance() {
		return instance;
	}
	
	public String getMusicMedia(String id) {
		return music.getPath(id);
	}
	
	public String getImageMedia(String id) {
		return images.getPath(id);
	}
	
	public String getStyleMedia(String id) {
		return styles.getPath(id);
	}

}

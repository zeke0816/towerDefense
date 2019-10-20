package media.databases;

import java.io.File;

import exceptions.DatabaseException;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;

public class MediaDatabase {
	
	private static final DatabaseService sounds = new SoundService();
	private static final DatabaseService images = new ImageService();
	private static final DatabaseService styles = new StyleService();
	private static final MediaDatabase instance = new MediaDatabase();

	protected MediaDatabase() {
		
	}
	
	public static MediaDatabase getInstance() {
		return instance;
	}
	
	public Media getSoundMedia(String id) throws DatabaseException {
		File file = new File(sounds.getPath(id));
		return new Media(file.toURI().toString());
	}
	
	public Image getImageMedia(String id) throws DatabaseException {
		File file = new File(images.getPath(id));
		return new Image(file.toURI().toString());
	}
	
	public Background getImageBackgroundMedia(String id, double width, double height, boolean contain, boolean cover) throws DatabaseException {
		return new Background(new BackgroundImage(getImageMedia(id), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, contain, cover)));
	}
	
	public String getStyleMedia(String id) throws DatabaseException {
		return styles.getPath(id);
	}

}

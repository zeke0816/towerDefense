package media;

import exceptions.DatabaseException;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import media.databases.DatabaseService;
import media.databases.ImageService;
import media.databases.SoundService;

public class MediaDatabase {
	
	private static final MediaDatabase instance = new MediaDatabase();

	private MediaDatabase() {
		
	}
	
	public static MediaDatabase getInstance() {
		return instance;
	}
	
	public Media getSoundMedia(String id) throws DatabaseException {
		DatabaseService sounds = new SoundService();
		return new Media(getClass().getResource(sounds.getPath(id)).toExternalForm());
	}
	
	public Image getImageMedia(String id) throws DatabaseException {
		DatabaseService images = new ImageService();
		return new Image(getClass().getResource(images.getPath(id)).toExternalForm());
	}
	
	public Background getImageBackgroundMedia(String id, double width, double height, boolean contain, boolean cover) throws DatabaseException {
		return new Background(new BackgroundImage(getImageMedia(id), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(width, height, false, false, contain, cover)));
	}

}

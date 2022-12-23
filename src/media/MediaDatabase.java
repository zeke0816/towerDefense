package media;

import java.net.URL;

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
	
	/**
	 * Gets the instance of this class
	 * @return the instance
	 */
	public static MediaDatabase getInstance() {
		return instance;
	}
	
	/**
	 * Gets a sound media from the database with a given ID
	 * @param id the ID of the media
	 * @return the sound
	 * @throws DatabaseException when the media resource could not be found
	 */
	public Media getSoundMedia(String id) throws DatabaseException {
		DatabaseService sounds = new SoundService();
		URL url = getClass().getResource(sounds.getPath(id));
		if(url == null) {
			throw new DatabaseException("The sound media could not be found on the database.");
		}
		return new Media(url.toExternalForm());
	}

	/**
	 * Gets an image from the database with a given ID
	 * @param id the ID of the image
	 * @return the image
	 * @throws DatabaseException when the image resource could not be found
	 */
	public Image getImageMedia(String id) throws DatabaseException {
		DatabaseService images = new ImageService();
		URL url = getClass().getResource(images.getPath(id));
		if(url == null) {
			throw new DatabaseException("The image media could not be found on the database.");
		}
		return new Image(url.toExternalForm());
	}
	
	/**
	 * Gets a background from an image found in the database using the given ID.
	 * The background position defaults to TOP_LEFT.
	 * @param id the ID
	 * @param width the width of the background
	 * @param height the height of the background
	 * @param contain true if the background should be contained by the node
	 * @param cover true if the background should cover the node
	 * @return the background
	 * @throws DatabaseException when the image resource could not be found
	 */
	public Background getImageBackgroundMedia(String id, double width, double height, boolean contain, boolean cover) throws DatabaseException {
		return new Background(new BackgroundImage(getImageMedia(id), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(width, height, false, false, contain, cover)));
	}

	/**
	 * Gets a background from an image found in the database using the given ID.
	 * @param id the ID
	 * @param width the width of the background
	 * @param height the height of the background
	 * @param contain true if the background should be contained by the node
	 * @param cover true if the background should cover the node
	 * @param position the Background Position
	 * @return the background
	 * @throws DatabaseException when the image resource could not be found
	 */
	public Background getImageBackgroundMedia(String id, double width, double height, boolean contain, boolean cover, BackgroundPosition position) throws DatabaseException {
		return new Background(new BackgroundImage(getImageMedia(id), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, position, new BackgroundSize(width, height, false, false, contain, cover)));
	}

}

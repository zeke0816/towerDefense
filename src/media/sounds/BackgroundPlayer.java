package media.sounds;

import exceptions.DatabaseException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import media.MediaDatabase;

/**
 * Class that plays the background music
 * @author zeke0816
 *
 */
public class BackgroundPlayer extends MusicPlayer {
	
	private static final BackgroundPlayer instance = new BackgroundPlayer();
	
	/**
	 * Initializes the background music player
	 */
	private BackgroundPlayer() {
		super();
	}

	/**
	 * Gets the instance of this class
	 * @return the instance
	 */
	public static BackgroundPlayer getInstance() {
		return instance;
	}
	
	/**
	 * Plays the background music
	 */
	public void play() {
		try {
			player = new MediaPlayer(MediaDatabase.getInstance().getSoundMedia("background"));
			fadeInTimeline = new Timeline(new KeyFrame(fadeDuration, new KeyValue(player.volumeProperty(), 1)));
			fadeOutTimeline = new Timeline(new KeyFrame(fadeDuration, new KeyValue(player.volumeProperty(), .1)));
			player.play();
		} catch (DatabaseException e) {
			System.out.println("The background music could not be loaded.");
		}
		player.setOnEndOfMedia(new Runnable() {
			
	        @Override
	        public void run() {
	        	player.seek(Duration.ZERO);
	        	player.play();
	        }
	        
	    });
	}
	
	/**
	 * Fade in the background music
	 */
	public void fadeIn() {
		fadeInTimeline.play();
	}

	/**
	 * Fade out the background music
	 */
	public void fadeOut() {
		fadeOutTimeline.play();
	}

}

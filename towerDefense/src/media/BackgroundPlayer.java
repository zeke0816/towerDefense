package media;

import exceptions.DatabaseException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import media.databases.MediaDatabase;

public class BackgroundPlayer {
	
	private MediaPlayer player;
	private Timeline fadeInTimeline;
	private Timeline fadeOutTimeline;
	private static final Duration fadeDuration = Duration.seconds(.5);
	private static final BackgroundPlayer instance = new BackgroundPlayer();
	
	/**
	 * Initializes the background music player
	 */
	private BackgroundPlayer() {
		try {
			player = new MediaPlayer(MediaDatabase.getInstance().getSoundMedia("background"));
			play();
		} catch (DatabaseException e) {
			System.out.println("The background music could not be loaded.");
		}
		
		fadeInTimeline = new Timeline(new KeyFrame(fadeDuration, new KeyValue(player.volumeProperty(), 1)));
		fadeOutTimeline = new Timeline(new KeyFrame(fadeDuration, new KeyValue(player.volumeProperty(), .1)));
	}
	
	public static BackgroundPlayer getInstance() {
		return instance;
	}
	
	/**
	 * Play the background music
	 */
	public void play() {
		player.play();
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

package media;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
		player = new MediaPlayer(new Media(getMediaFromPath("src/media/music/background.mp3")));
		play();
		
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
	
	//TODO: Use Proxy to get Media and stuff
	
	/**
	 * Gets media information as a String from a given path
	 * @param path the path to the media file
	 * @return the media information
	 */
	private String getMediaFromPath(String path) {
		return (new File(path)).toURI().toString();
	}

}

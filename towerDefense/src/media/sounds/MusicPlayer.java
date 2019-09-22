package media.sounds;

import javafx.animation.Timeline;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class that defines how to play sounds in the game
 * @author zeke0816
 *
 */
public abstract class MusicPlayer {
	
	protected MediaPlayer player;
	protected Timeline fadeInTimeline;
	protected Timeline fadeOutTimeline;
	protected static final Duration fadeDuration = Duration.seconds(.5);
	
	/**
	 * Initializes the background music player
	 */
	protected MusicPlayer() {
		
	}
	
	/**
	 * Fade in the music
	 */
	public void fadeIn() {
		fadeInTimeline.play();
	}

	/**
	 * Fade out the music
	 */
	public void fadeOut() {
		fadeOutTimeline.play();
	}

}

package media.sounds;

import exceptions.DatabaseException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import media.databases.MediaDatabase;

/**
 * Class that plays a sound when needed
 * @author zeke0816
 *
 */
public class SoundPlayer extends MusicPlayer {
	
	private static final SoundPlayer instance = new SoundPlayer();
	
	/**
	 * Initializes the sound player
	 */
	private SoundPlayer() {
		super();
	}
	
	/**
	 * Gets the instance of this class
	 * @return the instance
	 */
	public static SoundPlayer getInstance() {
		return instance;
	}

	/**
	 * Plays the sound
	 */
	public void play(String id) {
		try {
			Media sound = MediaDatabase.getInstance().getSoundMedia(id);
			BackgroundPlayer.getInstance().fadeOut();
			if(player != null && player.getStatus().equals(Status.PLAYING)) {
				player.stop();
			}
			player = new MediaPlayer(sound);
			player.play();
			player.setOnEndOfMedia(new Runnable() {
				
		        @Override
		        public void run() {
		        	BackgroundPlayer.getInstance().fadeIn();
		        }
		        
		    });
		} catch (DatabaseException e) {
			System.out.println("The Character's sound could not be played.");
		}
	}

}

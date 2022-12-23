package game;

/**
 * Class that handles the level variability
 * @author zeke0816
 *
 */
public class Level {
	
	protected int value;
	protected Wave wave;
	protected boolean isChanging;
	
	/**
	 * Initializes in Level 1 and a default multiplier
	 */
	public Level() {
		value = 1;
		wave = new Wave(this);
		isChanging = false;
	}
	
	/**
	 * Gets the level value
	 * @return the value of the current level
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Gets the Wave taking place
	 * @return the Wave
	 */
	public Wave getWave() {
		return wave;
	}
	
	/**
	 * Tells whether the level is changing or not
	 * @return true if it is changing, false if it is not
	 */
	public boolean isChanging() {
		return isChanging;
	}
	
	/**
	 * Stops the changing behavior
	 */
	public void stopChanging() {
		isChanging = false;
	}
	
	/**
	 * Resets the level
	 */
	public void reset() {
		value = 1;
		wave.reset();
	}
	
	/**
	 * Increase the level by 1 and calculate the new multiplier
	 */
	public void levelUp() {
		value++;
		isChanging = true;
		wave.waveUp();
		wave.resetValue();
	}

}

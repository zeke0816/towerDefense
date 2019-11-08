package game;

/**
 * Class that handles the level variability
 * @author zeke0816
 *
 */
public class Level {
	
	protected int value;
	protected int currentWave;
	protected Wave wave;
	
	/**
	 * Initializes in Level 1 and a default multiplier
	 */
	public Level() {
		value = 1;
		currentWave = 1;
		wave = new Wave();
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
	 * Increase the level by 1 and calculate the new multiplier
	 */
	public void levelUp() {
		currentWave++;
		if(currentWave == 4) {
			currentWave = 0;
			value++;
		}
		wave.levelUp();
	}

}

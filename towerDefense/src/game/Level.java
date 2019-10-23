package game;

/**
 * Class that handles the level variability
 * @author zeke0816
 *
 */
public class Level {
	
	protected int value;
	protected int multiplier;
	
	/**
	 * Initializes in Level 1 and a default multiplier
	 */
	public Level() {
		value = 1;
		multiplier = 1;
	}
	
	/**
	 * Gets the level value
	 * @return the value of the current level
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Gets the multiplier for object skills and features
	 * @return the multiplier for the current level
	 */
	public int getMultiplier() {
		return multiplier;
	}
	
	/**
	 * Increase the level by 1 and calculate the new multiplier
	 */
	public void increase() {
		value++;
		multiplier *= 1.25;
	}

}

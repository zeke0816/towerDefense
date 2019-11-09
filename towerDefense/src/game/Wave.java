package game;

/**
 * Class that handles the Wave variability
 * @author zeke0816
 *
 */
public class Wave {

	protected Level level;
	protected int deaths;
	protected int spawns;
	protected int spawnLimit;
	protected boolean isChanging;
	protected int value;
	
	/**
	 * Initializes the wave with 0 spawns and 30 as the limit
	 */
	public Wave(Level l) {
		deaths = 0;
		spawns = 0;
		spawnLimit = 5;
		isChanging = false;
		value = 1;
		level = l;
	}
	
	/**
	 * Adds a spawn to the counter
	 */
	public void spawn() {
		spawns++;
	}
	
	/**
	 * Adds a death to the counter
	 */
	public void kill() {
		deaths++;
		if(deaths == spawnLimit) {
			if(value < 3) {
				isChanging = true;
				waveUp();
			} else {
				level.levelUp();
			}
		}
	}
	
	/**
	 * Gets the number of spawns in this wave so far
	 */
	public int spawns() {
		return spawns;
	}
	
	/**
	 * Gets the number of deaths in this wave so far
	 */
	public int deaths() {
		return deaths;
	}
	
	/**
	 * Gets the spawn limit
	 */
	public int spawnLimit() {
		return spawnLimit;
	}

	/**
	 * Tells whether the wave is changing
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
	 * Gets the wave value
	 * @return the value of the current wave
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Resets the wave value
	 */
	public void resetValue() {
		value = 1;
	}
	
	/**
	 * Changes the variables when the Wave is over and has to be leveled up
	 */
	public void waveUp() {
		value++;
		spawns = 0;
		deaths = 0;
		spawnLimit += 1;
	}

}

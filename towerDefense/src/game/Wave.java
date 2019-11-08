package game;

/**
 * Class that handles the Wave variability
 * @author zeke0816
 *
 */
public class Wave {

	protected int deaths;
	protected int spawns;
	protected int spawnLimit;
	
	/**
	 * Initializes the wave with 0 spawns and 30 as the limit
	 */
	public Wave() {
		deaths = 0;
		spawns = 0;
		spawnLimit = 30;
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
	 * Changes the variables when the Wave is over and has to be leveled up
	 */
	public void levelUp() {
		spawns = 0;
		spawnLimit += 5;
	}

}

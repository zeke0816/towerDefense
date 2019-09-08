package game;

/**
 * Class that handles all things related to the game status and defaults
 * @author zeke0816
 *
 */
public class Game {
	
	protected Map map;
	protected Level level;
	protected Factory factory;
	protected boolean over;
	
	/**
	 * Initializes the game creating a map, level, and factory, plus marking the game as not over.
	 */
	public Game() {
		map = new Map();
		level = new Level();
		factory = new Factory();
		over = false;
	}
	
	/**
	 * Gets the map of the Game
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Creates a Warrior based on the given ID
	 * @param id the ID of the Warrior
	 * @return the newly created Warrior
	 */
	public Warrior createWarrior(String id) {
		// TODO: use the ID to create an actual Warrior, at least until implementing the Visitor design pattern
		return new Warrior();
	}
	
	/**
	 * End the game (Game Over)
	 */
	public void end() {
		over = true;
	}

}
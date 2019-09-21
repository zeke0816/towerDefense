package game;

/**
 * Class that handles all things related to the game status and defaults
 * @author zeke0816
 *
 */
public class Game {
	
	protected Map map;
	protected Level level;
	protected WarriorFactory factory;
	protected boolean over;
	
	/**
	 * Initializes the game creating a map, level, and factory, plus marking the game as not over.
	 */
	public Game() {
		map = new Map();
		level = new Level();
		factory = new WarriorFactory();
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
	 * Gets the factory of the Game
	 * @return the factory
	 */
	public WarriorFactory getFactory() {
		return factory;
	}
	
	/**
	 * End the game (Game Over)
	 */
	public void end() {
		over = true;
	}

}
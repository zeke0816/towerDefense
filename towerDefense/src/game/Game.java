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
	private static Game instance = new Game();
	
	/**
	 * Initializes the game creating a map, level, and factory, plus marking the game as not over.
	 */
	private Game() {
		map = new Map();
		level = new Level();
		factory = new Factory();
		over = false;
	}
	
	public static Game getInstance() {
		return instance;
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
	public Factory getFactory() {
		return factory;
	}
	
	/**
	 * End the game (Game Over)
	 */
	public void end() {
		over = true;
	}

}
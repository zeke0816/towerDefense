package game;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.CannotAffordException;
import game.objects.items.Item;
import game.objects.items.charm.temporary.TemporaryCharm;
import javafx.util.Pair;

/**
 * Class that handles all things related to the game status and defaults
 * @author zeke0816
 *
 */
public class Game {
	
	protected Map map;
	protected Inventory inventory;
	protected Level level;
	protected boolean beaten;
	protected boolean over;
	protected boolean paused;
	protected boolean started;
	protected double score;
	protected int budget;
	protected ArrayList<TemporaryCharm> temporaryCharms;
	protected HashMap<Item, Pair<Integer, Integer>> explosives;
	protected static final Game instance = new Game();
	
	/**
	 * Initializes the game creating a map, level, and factory, plus marking the game as not over.
	 */
	protected Game() {
		map = new Map();
		level = new Level();
		inventory = new Inventory();
		temporaryCharms = new ArrayList<TemporaryCharm>();
		explosives = new HashMap<Item, Pair<Integer, Integer>>();
		startNew();
	}
	
	/**
	 * Gets the instance of this class
	 * @return the instance of this class
	 */
	public static Game getInstance() {
		return instance;
	}
	
	/**
	 * Starts a new game
	 */
	public void startNew() {
		over = true;
		started = false;
		paused = false;
		beaten = false;
		score = 0;
		budget = 2500;
		map.flush();
		inventory.flush();
		temporaryCharms.clear();
		explosives.clear();
	}
	
	/**
	 * Gets the explosives of the game
	 * @return the explosives
	 */
	public HashMap<Item, Pair<Integer, Integer>> getExplosives() {
		return explosives;
	}
	
	/**
	 * Adds an explosive to the game
	 * @param it the explosive
	 * @param l the lane where it is located
	 * @param d the explosive's distance to the base
	 */
	public void addExplosives(Item it, int l, int d) {
		explosives.put(it, new Pair<Integer, Integer>(l, d));
	}
	
	/**
	 * Gets the map of the Game
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Gets the inventory of the Game
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Gets the current score 
	 * @return score
	 */
	public double getScore() {
		return score;
	}
	
	/**
	 * Gets the current budget 
	 * @return score
	 */
	public double getBudget() {
		return budget;
	}
	
	/**
	 * Gets the current level 
	 * @return score
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * Increases the game budget
	 * @param x money to be added to the budget
	 */
	public void increaseBudget(int x) {
		budget += x;
	}
	
	/**
	 * Decreases the game budget
	 * @param x money to be subtracted from the budget
	 * @throws CannotAffordException 
	 */
	public void decreaseBudget(int x) throws CannotAffordException {
		if(budget < x) {
			throw new CannotAffordException("Not enough budget!");
		}
		budget-=x;
	}
	
	/**
	 * Tells whether an amount of money can be afforded
	 * @param x the amount to be paid
	 * @return true if the player can afford it, false if not
	 */
	public boolean canAfford(int x) {
		return x <= budget;
	}
	
	/**
	 * Increases the game score 
	 * @param x points to be added to the score
	 */
	public void updateScore(int x) {
		score += x;
	}
	
	/**
	 * Updates the life of every active temporary charm and removes expired ones.
	 * @return a list of worn off charms.
	 */
	public ArrayList<TemporaryCharm> checkTemporaryCharms() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		ArrayList<TemporaryCharm> charms = new ArrayList<TemporaryCharm>();
		for(int i = 0; i < temporaryCharms.size(); i++) {
			TemporaryCharm charm = temporaryCharms.get(i);
			if(charm.decreaseLife()) {
				indexes.add(i);
			}
		}
		for(int index: indexes) {
			charms.add(temporaryCharms.remove(index));
		}
		return charms;
	}
	
	/**
	 * End the game (Game Over)
	 */
	public void end() {
		over = true;
		paused = false;
		beaten = false;
	}
	
	/**
	 * Beat the game (Victory)
	 */
	public void beat() {
		beaten = true;
		started = false;
		level.reset();
	}
	
	/**
	 * Start the game
	 */
	public void start() {
		started = true;
		over = false;
	}
	
	/**
	 * Tells whether the game is paused or not
	 * @return true if it is paused, false if it is not.
	 */
	public boolean paused() {
		return paused;
	}
	
	/**
	 * Pause the game
	 */
	public void pause() {
		paused = true;
	}
	
	/**
	 * Resume the game
	 */
	public void resume() {
		paused = false;
	}
	
	/**
	 * Tells whether the game is over or not
	 * @return true if the game is over, false if not.
	 */
	public boolean isOver() {
		return over;
	}
	
	/**
	 * Tells whether the game was beaten or not
	 * @return true if the game was beaten, false if not.
	 */
	public boolean isBeaten() {
		return beaten;
	}
	
	/**
	 * Tells whether the game has started or not
	 * @return true if the game has started, false if not.
	 */
	public boolean hasStarted() {
		return started;
	}

}
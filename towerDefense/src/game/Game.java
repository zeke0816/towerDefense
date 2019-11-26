package game;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.CannotAffordException;
import game.objects.GameObject;
import game.objects.items.Item;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.states.GameState;
import game.states.Welcome;
import javafx.util.Pair;
import visitors.BattleVisitor;

/**
 * Class that handles all things related to the game status and defaults
 * @author zeke0816
 *
 */
public class Game {
	
	protected Map map;
	protected int budget;
	protected Level level;
	protected double score;
	protected GameState state;
	protected BattleVisitor fight;
	protected Inventory inventory;
	public static final double sellingTax = .5;
	protected static final Game instance = new Game();
	protected ArrayList<TemporaryCharm> temporaryCharms;
	protected HashMap<Item, Pair<Integer, Integer>> explosives;
	
	/**
	 * Initializes the game creating a map, level, and factory, plus marking the game as not over.
	 */
	protected Game() {
		map = new Map();
		level = new Level();
		state = new Welcome(this);
		inventory = new Inventory();
		temporaryCharms = new ArrayList<TemporaryCharm>();
		explosives = new HashMap<Item, Pair<Integer, Integer>>();
		fight = new BattleVisitor();
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
	 * Gets the current state of the game
	 * @return the current state of the game
	 */
	public GameState state() {
		return state;
	}
	
	/**
	 * Changes the current state of the game
	 * @param s the new state of the game
	 */
	public void changeState(GameState s) {
		state = s;
	}
	
	/**
	 * Starts a new game
	 */
	public void startNew() {
		score = 0;
		budget = 10000;
		level.reset();
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
	public void increaseBudget(double x) {
		budget += x;
	}
	
	/**
	 * Decreases the game budget
	 * @param x money to be subtracted from the budget
	 * @throws CannotAffordException 
	 */
	public void decreaseBudget(double x) throws CannotAffordException {
		if(budget < x) {
			throw new CannotAffordException("Not enough budget!");
		}
		budget -= x;
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
	
	public void fight() {
		ArrayList<GameObject> objects = map.getGameObjectList();
		for(GameObject object: objects) {
			if(object.isAlive()) {
				object.accept(fight);
			}
		}
	}

}
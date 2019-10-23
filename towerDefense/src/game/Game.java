package game;

import java.util.ArrayList;

import game.objects.items.charm.temporary.TemporaryCharm;

/**
 * Class that handles all things related to the game status and defaults
 * @author zeke0816
 *
 */
public class Game {
	
	protected Map map;
	protected Level level;
	protected boolean over;
	protected double score;
	protected ArrayList<TemporaryCharm> temporaryCharms;
	private static final Game instance = new Game();
	
	/**
	 * Initializes the game creating a map, level, and factory, plus marking the game as not over.
	 */
	protected Game() {
		map = new Map();
		level = new Level();
		over = false;
		score = 0;
		
		temporaryCharms = new ArrayList<TemporaryCharm>();
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
	 * Gets the current score 
	 * @return score
	 */
	public double getScore() {
		return score;
	}
	
	/**
	 * Increases the game score 
	 * @param x points to be added to the score
	 */
	public void updateScore(int x) {
		score += x;
	}
	
	public void checkTemporaryCharms() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i = 0; i < temporaryCharms.size(); i++) {
			TemporaryCharm charm = temporaryCharms.get(i);
			if(charm.decreaseLife()) {
				indexes.add(i);
			}
		}
		for(int index: indexes) {
			temporaryCharms.remove(index);
		}
	}
	
	/**
	 * End the game (Game Over)
	 */
	public void end() {
		over = true;
		System.out.println("GAME OVER.");
	}
	
	public boolean isOver() {
		return over == true;
	}

}
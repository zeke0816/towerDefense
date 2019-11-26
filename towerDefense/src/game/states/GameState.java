package game.states;

import game.Game;
import visitors.GameVisitor;

/**
 * Abstract class that helps manage the state of the game.
 * @author zeke0816
 *
 */
public abstract class GameState {
	
	protected Game game;
	
	/**
	 * Initializes the state with a given game to control.
	 * @param c the character
	 */
	protected GameState(Game g) {
		game = g;
	}
	
	/**
	 * Performs an action
	 */
	public abstract void accept(GameVisitor v);
	
	/**
	 * Gets the game this state is acting on
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	
}

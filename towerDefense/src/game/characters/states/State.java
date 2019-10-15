package game.characters.states;

import game.characters.Character;

/**
 * Abstract class that helps manage the state of each character on the game.
 * @author zeke0816
 *
 */
public abstract class State {
	
	protected Character character;
	
	/**
	 * Initializes the state with a given character to control.
	 * @param c the character
	 */
	protected State(Character c) {
		character = c;
	}
	
	/**
	 * Performs an action set by the state on the character.
	 */
	public abstract void doAction();
	
	/**
	 * Undoes an action performed when the state was reached.
	 */
	public abstract void undoAction();
	
}

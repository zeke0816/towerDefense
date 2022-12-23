package game.objects.characters.states;

import game.objects.GameObject;

/**
 * Abstract class that helps manage the state of each character on the game.
 * @author zeke0816
 *
 */
public abstract class GameObjectState {
	
	protected GameObject object;
	
	/**
	 * Initializes the state with a given character to control.
	 * @param c the character
	 */
	protected GameObjectState(GameObject o) {
		object = o;
	}
	
	/**
	 * Performs the action
	 */
	public abstract void doAction();
	
	/**
	 * Undoes the action
	 */
	public abstract void undoAction();
	
}

package game.objects.characters.states;

import game.objects.GameObject;

/**
 * Shielded state for a game object
 * @author zeke0816
 *
 */
public class Shielded extends GameObjectState {

	/**
	 * Initializes the state knowing its context
	 * @param o the context
	 */
	public Shielded(GameObject o) {
		super(o);
	}

	@Override
	public void doAction() {
		object.setProtection(400);
	}
	
	@Override
	public void undoAction() {
		object.setProtection(0);
	}

}

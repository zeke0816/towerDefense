package game.objects.characters.states;

import game.objects.GameObject;

/**
 * Basic state for a Game Object
 * @author zeke0816
 *
 */
public class Basic extends GameObjectState {

	/**
	 * Initializes the state knowing its context
	 * @param o the context
	 */
	public Basic(GameObject o) {
		super(o);
	}

	@Override
	public void doAction() {
		
	}

	@Override
	public void undoAction() {
		
	}

}

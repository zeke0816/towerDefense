package game.objects.characters.states;

import game.objects.GameObject;

/**
 * Boosted state for a Game Object
 * @author zeke0816
 *
 */
public class Boosted extends GameObjectState {

	/**
	 * Initializes the state knowing its context
	 * @param o the context
	 */
	public Boosted(GameObject o) {
		super(o);
	}

	@Override
	public void doAction() {
		object.increaseAttackFrequency(4);
		object.increaseStrength(500);
	}

	@Override
	public void undoAction() {
		object.decreaseAttackFrequency(4);
		object.decreaseStrength(500);
	}

}

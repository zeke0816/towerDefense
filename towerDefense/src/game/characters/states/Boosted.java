package game.characters.states;

import game.GameObject;

public class Boosted extends State {
	
	protected GameObject object;

	public Boosted(GameObject o) {
		super(o);
		object = o;
	}

	@Override
	public void doAction() {
		object.increaseAttackSpeed(1.2);
		object.increaseStrength(1.2);
	}

	@Override
	public void undoAction() {
		object.decreaseAttackSpeed(1.2);
		object.decreaseStrength(1.2);
	}

}

package game.objects.characters.states;

import game.objects.GameObject;

public class Boosted extends State {
	
	protected GameObject object;

	public Boosted(GameObject o) {
		super(o);
		object = o;
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

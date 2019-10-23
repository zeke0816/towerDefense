package game.objects.characters.states;

import game.GameObject;

public class Shielded extends State {

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

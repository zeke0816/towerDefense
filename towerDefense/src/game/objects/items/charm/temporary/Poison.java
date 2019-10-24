package game.objects.items.charm.temporary;

import game.objects.GameObject;

public class Poison extends TemporaryCharm{

	public Poison() {
		price = 1500;
	}
	
	private Poison(Poison target) {
        super(target);
	}
	
	@Override
	public Poison clone() {
		return new Poison(this);
	}

	@Override
	public boolean undoAction() {
		return object.unpoison();
	}

	@Override
	public boolean doAction(GameObject o) {
		object = o;
		return o.poison();
	}
}	

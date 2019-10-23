package game.objects.items.charm.permanent;

import game.objects.GameObject;

public class Cure extends PermanentCharm {

	public Cure() {
		price = 1000;
	}
	
	protected Cure(Cure target) {
		super(target);
	}
	
	@Override
	public Cure clone() {
		return new Cure(this);
	}

	@Override
	public boolean doAction(GameObject o) {
		return o.cure();
	}
		
}

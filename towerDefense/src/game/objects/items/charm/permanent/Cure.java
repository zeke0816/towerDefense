package game.objects.items.charm.permanent;

import game.objects.GameObject;

public class Cure extends PermanentCharm {

	public Cure() {
		price = 1000;
	}
	
	private Cure(Cure target) {
		super(target);
	}
	
	@Override
	public PermanentCharm clone() {
		return new Cure(this);
	}

	@Override
	public void doAction(GameObject o) {
		o.cure();
	}
		
}

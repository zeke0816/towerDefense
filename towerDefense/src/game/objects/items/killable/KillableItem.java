package game.objects.items.killable;

import game.objects.items.Item;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.visitors.Visitor;

/**
 * Killable Item class
 */
public abstract class KillableItem extends Item {
	
	protected KillableItem() {
		
	}
	
	protected KillableItem(KillableItem target) {
		super(target);
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public boolean attack(KillableItem k) {
		int harm = k.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}

	public boolean attack(TemporaryCharm t) {
		return false;
	}

	public boolean attack(PermanentCharm p) {
		return false;
	}
	
}

package game.objects.items.killable;

import game.objects.items.Item;
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
	
}

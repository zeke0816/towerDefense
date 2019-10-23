package game.objects.items.killable;

import game.objects.items.Item;

/**
 * Killable Item class
 */
public abstract class KillableItem extends Item {
	
	protected KillableItem() {
		
	}
	
	protected KillableItem(KillableItem target) {
		super(target);
	}
}

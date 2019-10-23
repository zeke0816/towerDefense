package game.objects.items.charm;

import game.objects.GameObject;
import game.objects.items.Item;

public abstract class CharmingItem extends Item{

	
	protected CharmingItem() {
	
	}
	
	protected CharmingItem(CharmingItem target) {
		super(target);
	}
	
	public abstract void doAction(GameObject o);

}

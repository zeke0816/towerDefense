package game.objects.items.charm;

import game.objects.GameObject;
import game.objects.items.Item;

public abstract class CharmingItem extends Item{

	
	protected CharmingItem() {
		LIFE = 0;
		STRENGTH = 0;
	}
	
	protected CharmingItem(CharmingItem target) {
		super(target);
	}
	
	public abstract boolean doAction(GameObject o);

}

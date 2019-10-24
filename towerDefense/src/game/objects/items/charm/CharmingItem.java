package game.objects.items.charm;

import game.objects.GameObject;
import game.objects.items.Item;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;

public abstract class CharmingItem extends Item{

	
	protected CharmingItem() {
		LIFE = 0;
		STRENGTH = 0;
	}
	
	protected CharmingItem(CharmingItem target) {
		super(target);
	}
	
	public boolean attack(KillableItem k) {
		return false;
	}

	public boolean attack(TemporaryCharm t) {
		return false;
	}

	public boolean attack(PermanentCharm p) {
		return false;
	}
	
	public abstract boolean doAction(GameObject o);

}

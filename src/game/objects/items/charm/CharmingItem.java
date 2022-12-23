package game.objects.items.charm;

import java.util.ArrayList;

import game.objects.GameObject;
import game.objects.items.Item;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;

/**
 * Class that defines a Charming Item (a spell)
 * @author zeke0816
 *
 */
public abstract class CharmingItem extends Item {
	
	/**
	 * Initializes a Charming item with no life nor strength
	 */
	protected CharmingItem() {
		LIFE = 0;
		STRENGTH = 0;
	}
	
	/**
	 * Initializes a Charming Item cloning from another one
	 * @param target
	 */
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
	
	/**
	 * Applies the charm onto a Game Object
	 * @param o the Game Object the charm is being applied on
	 * @return true if it was applied, false if not
	 */
	public abstract boolean doAction(GameObject o);
	
	public ArrayList<GameObject> fight() {
		return new ArrayList<GameObject>();
	}

}

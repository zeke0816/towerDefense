package game.objects.items.charm.temporary;

import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.CharmingItem;
import game.visitors.Visitor;

/**
 * Temporary Item class
 */
public abstract class TemporaryCharm extends CharmingItem {
	
	protected GameObject object;
	
	protected TemporaryCharm() {
		
	}
	
	protected TemporaryCharm(TemporaryCharm target) {
		super(target);
	}

	@Override
	public boolean attack(Warrior w) {
		return false;
	}

	@Override
	public boolean attack(Enemy w) {
		return false;
	}
	
	/**
	 * Decreases the life of the Spell
	 * @return true if the Spell wore off, false if it is still active
	 */
	public boolean decreaseLife() {
		life--;
		boolean woreOff = life == 0;
		if(woreOff) {
			undoAction();
		}
		return woreOff;
	}
	
	/**
	 * Gets the Game Object associated with this Spell
	 * @return the Game Object affected by this Spell
	 */
	public GameObject getObject() {
		return object;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public abstract boolean undoAction();

}

package game.items;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;

/**
 * Temporary Item class
 */
public abstract class TemporaryItem extends Item {

	protected TemporaryItem() {
		
	}
	
	protected TemporaryItem(Item target) {
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
	 * Method responsible for decreasing the time of the item.
	 */
	public void decreaseLife() {
		life --;
		
		//TODO : DELETE ITEM FROM ITEM LAYOUT, AND FROM LOGIC(USING GUI)
	}
	
	/**
	 * Creates and return a copy of this TemporaryItem
	 */
	public abstract TemporaryItem clone();
	
}

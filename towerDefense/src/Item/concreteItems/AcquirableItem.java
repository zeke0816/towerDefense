package Item.concreteItems;

import Item.Item;
import game.characters.Enemy;
import game.characters.Warrior;

/**
 * Acquirable Item class
 */
public class AcquirableItem extends Item {

	@Override
	public boolean attack(Warrior w) {
		return false;
	}

	@Override
	public boolean attack(Enemy w) {
		return false;
	}

}

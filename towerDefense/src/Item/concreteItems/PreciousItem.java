package Item.concreteItems;

import Item.Item;
import game.characters.Enemy;
import game.characters.Warrior;

/**
 * Precious Item class
 */
public class PreciousItem extends Item {

	@Override
	public boolean attack(Warrior w) {
		return false;
	}

	@Override
	public boolean attack(Enemy w) {
		return false;
	}

}

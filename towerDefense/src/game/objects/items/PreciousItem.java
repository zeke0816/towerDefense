package game.objects.items;

import game.objects.characters.Enemy;
import game.objects.characters.Warrior;

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

package game.objects.items;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;

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

package game.items;

import game.characters.Enemy;
import game.characters.Warrior;

/**
 * Random Item class
 */
public class RandomItem extends Item {

	@Override
	public boolean attack(Warrior w) {
		return false;
	}

	@Override
	public boolean attack(Enemy w) {
		return false;
	}

}

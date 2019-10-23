package game.objects.items;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;

/**
 * Acquirable Item class
 */
public class KillableItem extends Item {
	
	protected KillableItem() {
		
	}
	
	protected KillableItem(KillableItem target) {
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
	
	@Override
	public KillableItem clone() {
		return new KillableItem(this);
	}

}

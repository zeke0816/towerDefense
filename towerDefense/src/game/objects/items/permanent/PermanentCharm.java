package game.objects.items.permanent;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;

/**
 * Permanent charm Item class
 */
public class PermanentCharm extends Item {
	
	protected PermanentCharm() {
		
	}
	
	protected PermanentCharm(PermanentCharm target) {
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
	public PermanentCharm clone() {
		return new PermanentCharm(this);
	}

}

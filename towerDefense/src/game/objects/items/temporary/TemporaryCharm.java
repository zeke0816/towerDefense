package game.objects.items.temporary;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;

/**
 * Temporary Item class
 */
public class TemporaryCharm extends Item {
	
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
	
	@Override
	public TemporaryCharm clone() {
		return new TemporaryCharm(this);
	}

}

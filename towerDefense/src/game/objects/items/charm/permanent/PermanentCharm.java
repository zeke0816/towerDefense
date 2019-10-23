package game.objects.items.charm.permanent;

import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.CharmingItem;

/**
 * Permanent charm Item class
 */
public abstract class PermanentCharm extends CharmingItem {
	
	protected PermanentCharm() {
		scope = 0;
		life = 0;
		strength = 0;
		attackSpeed = 0;
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
	public abstract PermanentCharm clone();


}

package game.objects.items.charm.permanent;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.CharmingItem;
import visitors.Visitor;

/**
 * Permanent charm Item class
 */
public abstract class PermanentCharm extends CharmingItem {
	
	protected PermanentCharm() {
		scope = 0;
		life = 0;
		strength = 0;
		attackFrequency = 0;
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
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public abstract PermanentCharm clone();


}

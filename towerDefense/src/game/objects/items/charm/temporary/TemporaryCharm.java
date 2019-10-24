package game.objects.items.charm.temporary;

import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.CharmingItem;
import game.visitors.Visitor;

/**
 * Temporary Item class
 */
public abstract class TemporaryCharm extends CharmingItem {
	
	protected GameObject object;
	
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
	
	public boolean decreaseLife() {
		life--;
		boolean dead = (life == 0);
		if(dead) {
			undoAction(object);
		}
		return dead;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public abstract TemporaryCharm clone();
	
	public abstract boolean undoAction(GameObject o);

}

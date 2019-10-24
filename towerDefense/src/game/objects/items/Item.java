package game.objects.items;

import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;

/**
 * Abstract Item Class
 */
public abstract class Item extends GameObject {
	
	protected boolean squaredScope;

	protected Item() {
		squaredScope = false;
		points = 0;
		movementSpeed = 0;
	}
	
	protected Item(Item target) {
		super(target);
		if(target != null) {
			squaredScope = target.hasSquaredScope();
		}
	}

	public boolean attack(Warrior w) {
		return false;
	}
	
	public boolean attack(Enemy e) {
		int harm = e.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}
	
	public boolean hasSquaredScope() {
		return squaredScope;
	}
	
	/**
	 * Creates and returns a copy of this Item
	 */
	public abstract Item clone();
	
}

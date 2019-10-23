package game.objects.characters.warriors;

import game.objects.characters.Character;
import game.objects.characters.enemies.Enemy;
import game.objects.items.Item;
import game.visitors.Visitor;

/**
 * Abstract class that helps define the warriors in the game
 * @author zeke0816
 *
 */
public abstract class Warrior extends Character {
	
	protected Warrior() {
		movementSpeed = 0;
	}
	
	protected Warrior(Warrior target) {
		super(target);
	}

	public void accept(Visitor v) {
		v.visit(this);
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
	
	public boolean attack(Item i) {
		return false;
	}

	/**
	 * Creates and returns a copy of this Warrior
	 */
	public abstract Warrior clone();

}

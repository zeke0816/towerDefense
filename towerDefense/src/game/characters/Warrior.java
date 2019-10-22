package game.characters;

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
		life -= e.getStrength();
		return true;
	}

	/**
	 * Creates and returns a copy of this Warrior
	 */
	public abstract Warrior clone();

}

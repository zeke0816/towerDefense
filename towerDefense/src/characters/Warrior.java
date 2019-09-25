package characters;

import game.Visitor;

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
	
	/**
	 * Accept method, Inherited from GameObject
	 * Sends to the visitor, the "Warrior" object
	 */
	public void accept(Visitor v) {
		v.visitWarrior(this);
	}

	/**
	 * Creates and returns a copy of this Warrior
	 */
	public abstract Warrior clone();

}

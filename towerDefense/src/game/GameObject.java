package game;

import game.characters.Enemy;
import game.characters.Warrior;
import game.visitors.Visitor;

/**
 * Abstract class that helps define the objects in the game
 * @author zeke0816
 *
 */
public abstract class GameObject {
	
	protected int life;
	protected int points;
	protected int movementSpeed;
	
	protected GameObject() {
		
	}
	
	protected GameObject(GameObject target) {
		if(target != null) {
			life = target.getLife();
			points = target.getPoints();
            movementSpeed = target.getMovementSpeed();
		}
	}
	
	/**
	 * Determines if the object is alive
	 * @return true if the object is alive, false if not.
	 */
	public boolean isAlive() {
		return life > 0;
	}
	
	/**
	 * Determines if the object is dead
	 * @return true if the object is dead, false if not.
	 */
	public boolean isDead() {
		return !isAlive();
	}
	
	/**
	 * Gets the points given by this object
	 * @return the points for this object
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Gets the life of the object
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Gets the movement speed of the object
	 * @return the movement speed
	 */
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	/**
	 * Determines what happens if a Warrior tries to attack this character
	 * @param w the Warrior trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public abstract boolean attack(Warrior w);

	/**
	 * Determines what happens if an Enemy tries to attack this character
	 * @param w the Enemy trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public abstract boolean attack(Enemy w);
	
	/**
	 * Accepts a Visitor and delegates some concrete operations to it
	 * @param v the visitor to accept
	 */
	public abstract void accept(Visitor v);

}

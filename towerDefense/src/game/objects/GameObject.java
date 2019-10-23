package game.objects;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.states.Basic;
import game.objects.characters.states.State;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
import game.objects.items.killable.Nuke;
import game.visitors.Visitor;

/**
 * Abstract class that helps define the objects in the game
 * @author zeke0816
 *
 */
public abstract class GameObject {

	protected State state;
	protected int DEF_LIFE;
	protected int life;
	protected int scope;
	protected int price;
	protected int points;
	protected int strength;
	protected int protection;
	protected int attackSpeed;
	protected int movementSpeed;
	
	protected GameObject() {
		state = new Basic(this);
		protection = 0;
	}
	
	protected GameObject(GameObject target) {
		if(target != null) {
			life = target.getLife();
        	scope = target.getScope();
			state = target.getState();
			points = target.getPoints();
            strength = target.getStrength();
            protection = target.getProtection();
            attackSpeed = target.getAttackSpeed();
            movementSpeed = target.getMovementSpeed();
		}
	}
	
	/**
	 * Gets the state of the object
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Changes the state of the object
	 * @param s the state
	 */
	public void changeState(State s) {
		state.undoAction();
		state = s;
		state.doAction();
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
	 * Gets the protection of the object
	 * @return the protection
	 */
	public int getProtection() {
		return protection;
	}

	/**
	 * Gets the scope of the object
	 * @return the scope
	 */
	public int getScope() {
		return scope;
	}

	/**
	 * Gets the strength of the object
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * Gets the price of the object
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Gets the attack speed of the object
	 * @return the attack speed
	 */
	public int getAttackSpeed() {
		return attackSpeed;
	}

	/**
	 * Gets the movement speed of the object
	 * @return the movement speed
	 */
	public int getMovementSpeed() {
		return movementSpeed;
	}

	/**
	 * Sets the protection of the object
	 * @return the protection
	 */
	public void setProtection(int p) {
		protection = p;
	}

	/**
	 * Increases the attack speed of the object
	 * @param f the factor
	 */
	public void increaseAttackSpeed(double f) {
		attackSpeed *= f;
	}

	/**
	 * Increases the strength of the object
	 * @param f the factor
	 */
	public void increaseStrength(double f) {
		strength *= f;
	}

	/**
	 * Decreases the attack speed of the object
	 * @param f the factor
	 */
	public void decreaseAttackSpeed(double f) {
		attackSpeed /= f;
	}

	/**
	 * Decreases the strength of the object
	 * @param f the factor
	 */
	public void decreaseStrength(double f) {
		strength /= f;
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
	 * Determines what happens if an Item tries to attack this character
	 * @param w the Item trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public abstract boolean attack(Item i);
	
	/**
	 * Determines what happens if the Nuke tries to attack this character
	 * @param n the Nuke trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public boolean attack(Nuke n) {
		int harm = n.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}
	
	public void cure() {
		if(life < DEF_LIFE ) {
			life = DEF_LIFE;
		}
	}
	
	/**
	 * Accepts a Visitor and delegates some concrete operations to it
	 * @param v the visitor to accept
	 */
	public abstract void accept(Visitor v);

}

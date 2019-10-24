package game.objects;

import java.util.Random;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.states.Basic;
import game.objects.characters.states.State;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.CharmingItem;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import game.visitors.Visitor;

/**
 * Abstract class that helps define the Game Objects in the game
 * @author zeke0816
 *
 */
public abstract class GameObject {

	protected State state;
	protected int LIFE;
	protected int STRENGTH;
	protected int life;
	protected int scope;
	protected int price;
	protected int points;
	protected int strength;
	protected boolean drops;
	protected int protection;
	protected int attackSpeed;
	protected int movementSpeed;
	
	/**
	 * Initializes the Game Object in a Basic state, not being able to drop items and without protection
	 */
	protected GameObject() {
		state = new Basic(this);
		protection = 0;
		drops = false;
	}
	
	/**
	 * Initializes the Game Object by copying the attributes of the target Game Object onto this object
	 * @param target the prototype Game Object
	 */
	protected GameObject(GameObject target) {
		if(target != null) {
			life = target.getLife();
        	scope = target.getScope();
			state = target.getState();
			drops = target.getDrops();
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
	 * Gets the value of the drops attribute
	 * @return true if it may drop, false if it may not
	 */
	public boolean getDrops() {
		return drops;
	}
	
	/**
	 * If the Game Object can drop, figure out if it will drop this time around
	 * @return true if it will drop something, false if it will not
	 */
	public boolean drops() {
		boolean res = drops;
		if(drops) {
			Random r = new Random();
			int rand = r.nextInt(2);
			res = rand == 0;
		}
		return res;
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
	 * Determines what happens if an Killable Item tries to attack this character
	 * @param w the Killable Item trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public abstract boolean attack(KillableItem i);

	/**
	 * Determines what happens if an Temporary Charm tries to attack this character
	 * @param w the Temporary Charm trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public abstract boolean attack(TemporaryCharm i);

	/**
	 * Determines what happens if an Permanent Charm tries to attack this character
	 * @param w the Permanent Charm trying to attack
	 * @return true if the attack was successful, false if not.
	 */
	public abstract boolean attack(PermanentCharm i);
	
	/**
	 * Cures a Game Object entirely from its injuries, returning its life to the default value unless it has more life than that.
	 * @return true if he needed to be cured and was successfully cured, false in any other case.
	 */
	public boolean cure() {
		boolean needsCure = life < LIFE;
		if(needsCure) {
			life = LIFE;
			System.out.println("I needed that! Thanks.");
		} else {
			System.out.println("I don't need that! Give it to someone else, don't waste it! Thanks.");
		}
		return needsCure;
	}
	
	/**
	 * Poisons the Game Object, removing its ability to harm other Game Objects.
	 * @return true if the object was poisoned
	 */
	public boolean poison() {
		strength = 0;
		return true;
	}
	
	/**
	 * Cures the Game Object from a poison previously administered, returning its strength to the default value
	 * @return true if the object was cured
	 */
	public boolean unpoison() {
		strength = STRENGTH;
		return true;
	}
	
	/**
	 * Applies the spell onto this Game Object
	 * @param i the spell
	 * @return true if it was applied and needed by the object, false in any other case.
	 */
	public boolean applyItem(CharmingItem i) {
		return i.doAction(this);
	}
	
	
	/**
	 * Accepts a Visitor and delegates some concrete operations to it
	 * @param v the visitor to accept
	 */
	public abstract void accept(Visitor v);

}

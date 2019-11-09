package game.objects;

import java.util.Random;

import exceptions.CannotAffordException;
import exceptions.UnavailableObjectException;
import game.Game;
import game.Inventory;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.states.Basic;
import game.objects.characters.states.State;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.CharmingItem;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import visitors.Visitor;

/**
 * Abstract class that helps define the Game Objects in the game
 * @author zeke0816
 *
 */
public abstract class GameObject {

	protected State state;
	protected String id;
	protected String key;
	protected String name;
	protected int lane;
	protected int life;
	protected int LIFE;
	protected int scope;
	protected int price;
	protected int points;
	protected int strength;
	protected int STRENGTH;
	protected int distance;
	protected boolean drops;
	protected int protection;
	protected boolean playsSound;
	protected int attackAttempts;
	protected int attackFrequency;
	protected int movementAttempts;
	protected boolean takesTwoCells;
	protected int movementFrequency;
	protected final int increment = 200;
	
	/**
	 * Initializes the Game Object in a Basic state, not being able to drop items and without protection
	 */
	protected GameObject() {
		id = "na";
		key = "_na";
		name = "N/A";
		drops = false;
		protection = 0;
		playsSound = false;
		attackFrequency = 4;
		takesTwoCells = false;
		movementFrequency = 10;
		state = new Basic(this);
		resetAttackAttempts();
		resetMovementAttempts();
	}
	
	/**
	 * Initializes the Game Object by copying the attributes of the target Game Object onto this object
	 * @param target the prototype Game Object
	 */
	protected GameObject(GameObject target) {
		if(target != null) {
			id = target.getID();
			key = target.getKey();
        	name = target.getName();
			life = target.getLife();
        	scope = target.getScope();
			state = target.getState();
			drops = target.getDrops();
			price = target.getPrice();
			points = target.getPoints();
            strength = target.getStrength();
            playsSound = target.playsSound();
            protection = target.getProtection();
            takesTwoCells = target.takesTwoCells();
            attackAttempts = target.getAttackAttempts();
            attackFrequency = target.getAttackFrequency();
            movementAttempts = target.getMovementAttempts();
            movementFrequency = target.getMovementFrequency();
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
	 * Buys this Game Object
	 * @throws CannotAffordException if the Game Object is not affordable
	 */
	public void buy() throws CannotAffordException {
		Game game = Game.getInstance();
		game.decreaseBudget(price);
		game.getInventory().add(id);
	}
	
	/**
	 * Uses this Game Object, taking it from the Inventory
	 * @return true if there are more Objects like this in the Inventory, false if not
	 * @throws UnavailableObjectException if the Game Object is not available in the Inventory
	 */
	public boolean use() throws UnavailableObjectException {
		Inventory inventory = Game.getInstance().getInventory();
		inventory.take(id);
		return inventory.available(id);
	}
	
	/**
	 * Saves this Game Object in the Inventory
	 */
	public void save() {
		Game.getInstance().getInventory().add(id);
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
	 * Gets the ID
	 * @return the ID
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Gets the Key
	 * @return the Key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Gets the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the lane where this object is located
	 * @return the lane
	 */
	public int getLane() {
		return lane;
	}
	
	/**
	 * Gets the object's distance to the base
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
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
	 * Tells whether the object plays a sound
	 * @return true if it does, false if not
	 */
	public boolean playsSound() {
		return playsSound;
	}
	
	/**
	 * Tells whether the object takes two cells (vertically)
	 * @return true if it can, false if not
	 */
	public boolean takesTwoCells() {
		return takesTwoCells;
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
	public int getAttackFrequency() {
		return attackFrequency;
	}

	/**
	 * Gets the number of attack attempts
	 * @return the attempts to attack so far
	 */
	public int getAttackAttempts() {
		return attackAttempts;
	}

	/**
	 * Resets the attack attempts
	 */
	public void resetAttackAttempts() {
		attackAttempts = 0;
	}

	/**
	 * Gets the movement attempts of the object
	 * @return the movement attempts
	 */
	public int getMovementAttempts() {
		return movementAttempts;
	}

	/**
	 * Attempts to move
	 */
	public void move() {
		movementAttempts++;
	}

	/**
	 * Resets the movement attempts
	 */
	public void resetMovementAttempts() {
		movementAttempts = 0;
	}

	/**
	 * Gets the movement speed of the object
	 * @return the movement speed
	 */
	public int getMovementFrequency() {
		return movementFrequency;
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
	public void increaseAttackFrequency(int f) {
		attackFrequency -= f;
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
	public void increaseStrength(int f) {
		strength += f;
	}

	/**
	 * Decreases the attack speed of the object
	 * @param f the factor
	 */
	public void decreaseAttackFrequency(int f) {
		attackFrequency += f;
	}

	/**
	 * Decreases the strength of the object
	 * @param f the factor
	 */
	public void decreaseStrength(int f) {
		strength -= f;
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
	 * Sets the lane where this object is located
	 * @param l the lane
	 */
	public void setLane(int l) {
		lane = l;
	}
	
	/**
	 * Sets the object's distance to the base
	 * @param d the distance
	 */
	public void setDistance(int d) {
		distance = d;
	}

	/**
	 * Increments the life and strength of the enemy
	 */
	public void growStats() {
		LIFE += increment;
		STRENGTH += increment;
	}
	
	/**
	 * Accepts a Visitor and delegates some concrete operations to it
	 * @param v the visitor to accept
	 */
	public abstract void accept(Visitor v);
	
	public abstract GameObject clone();

}

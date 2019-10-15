package game.characters;

import game.GameObject;
import game.characters.states.Basic;
import game.characters.states.State;

/**
 * Abstract class that helps define a character in the game
 * @author zeke0816
 *
 */
public abstract class Character extends GameObject {
	
	protected State state;
	protected int scope;
	protected int strength;
	protected int protection;
	protected int attackSpeed;
	protected int movementSpeed;
	
	protected Character() {
		state = new Basic(this);
	}
	
	protected Character(Character target) {
		super(target);
        if(target != null) {
        	state = target.getState();
        	scope = target.getScope();
            strength = target.getStrength();
            protection = target.getProtection();
            attackSpeed = target.getAttackSpeed();
            movementSpeed = target.getMovementSpeed();
        }
    }
	
	public State getState() {
		return state;
	}
	
	public int getScope() {
		return scope;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getProtection() {
		return protection;
	}
	
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public int getMovementSpeed() {
		return movementSpeed;
	}
	
	public void setScope(int s) {
		scope = s;
	}
	
	public void setStrength(int s) {
		strength = s;
	}
	
	public void setProtection(int p) {
		protection = p;
	}
	
	public void setAttackSpeed(int s) {
		attackSpeed = s;
	}
	
	public void setMovementSpeed(int s) {
		movementSpeed = s;
	}

}

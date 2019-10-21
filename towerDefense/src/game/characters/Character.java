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

}
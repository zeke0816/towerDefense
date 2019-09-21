package characters;

import characters.states.Basic;
import characters.states.State;
import game.GameObject;

/**
 * Abstract class that helps define a character in the game
 * @author zeke0816
 *
 */
public abstract class Character extends GameObject {
	
	protected State state;
	protected int strength;
	protected int protection;
	protected int attackSpeed;
	protected int movementSpeed;
	
	protected Character() {
		state = new Basic(this);
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

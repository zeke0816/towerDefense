package game.characters;

import game.GameObject;

/**
 * Abstract class that helps define a character in the game
 * @author zeke0816
 *
 */
public abstract class Character extends GameObject {
	
	protected Character() {
		
	}
	
	protected Character(Character target) {
		super(target);
    }

}
package characters;

/**
 * Abstract class that helps define the enemies in the game
 * @author zeke0816
 *
 */
public abstract class Enemy extends Character {
	protected Enemy() {
		
	}
	
	protected Enemy(Enemy target) {
		super(target);
	}
	
	/**
	 * Creates and returns a copy of this Enemy.
	 */
	public abstract Enemy clone();
}
package characters;

import game.Visitor;

/**
 * Abstract class that helps define the enemies in the game
 * @author zeke0816
 *
 */
public abstract class Enemy extends Character {
	
	protected int points;
	
	protected Enemy() {
		points = 0;
	}
	
	protected Enemy(Enemy target) {
		super(target);
		points = target.getPoints();
	}
	
	/**
	 * Enemy Points
	 * @return points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Accept method, Inherited from GameObject
	 * Sends to the visitor, the "Enemy" object
	 */
	public void accept(Visitor v) {
		v.visitEnemy(this);
	}
	
	/**
	 * Creates and returns a copy of this Enemy.
	 */
	public abstract Enemy clone();
}
package game.characters;

import game.visitors.Visitor;

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
	
	public void accept(Visitor v) {
		v.visit(this);
	}

	public boolean attack(Warrior w) {
		life -= w.getStrength();
		if(life < 0){
			life = 0;
			// TODO: KILL
		}
		return true;
	}
	
	public boolean attack(Enemy e) {
		return false;
	}
	
	/**
	 * Creates and returns a copy of this Enemy.
	 */
	public abstract Enemy clone();
	
}
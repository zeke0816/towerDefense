package game.objects.characters.enemies;

import game.objects.characters.Character;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
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
		int harm = w.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}
	
	public boolean attack(Enemy e) {
		return false;
	}
	
	public boolean attack(Item i) {
		int harm = i.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}
	
	/**
	 * Creates and returns a copy of this Enemy.
	 */
	public abstract Enemy clone();
	
}
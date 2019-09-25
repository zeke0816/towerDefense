package game;

import Item.Item;
import characters.Enemy;
import characters.Warrior;

/**
 * Visitor Interface, contains the declaration of all possible GameObjects
 */
public interface Visitor {
	/**
	 * Visits the enemy
	 * @param e enemy
	 */
	public void visitEnemy(Enemy e);
	
	/**
	 * Visits the Warrior
	 * @param w warrior
	 */
	public void visitWarrior(Warrior w);
	
	/**
	 * Visits the Item
	 * @param i Item
	 */
	public void visitItem(Item i);
	
}

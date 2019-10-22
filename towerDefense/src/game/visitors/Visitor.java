package game.visitors;

import game.characters.Enemy;
import game.characters.Warrior;
import game.items.Item;

/**
 * Visitor Interface, contains the declaration of all possible GameObjects
 */
public interface Visitor {
	
	/**
	 * Visits the enemy
	 * @param e enemy
	 */
	public void visit(Enemy e);
	
	/**
	 * Visits the Warrior
	 * @param w warrior
	 */
	public void visit(Warrior w);
	
	/**
	 * Visits the Item
	 * @param i Item
	 */
	public void visit(Item i);
	
}

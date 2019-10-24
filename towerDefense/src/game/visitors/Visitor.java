package game.visitors;

import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;

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
	 * Visits a permanent spell
	 * @param c permanent spell
	 */
	public void visit(PermanentCharm p);
	
	/**
	 * Visits a temporary spell
	 * @param k temporary spell
	 */
	public void visit(TemporaryCharm t);
	
	/**
	 * Visits a killable item
	 * @param k killable item
	 */
	public void visit(KillableItem k);
	
}

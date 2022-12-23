package gui.factories.difficulty;

import java.util.ArrayList;
import java.util.Random;

import gui.factories.EnemyFactory;
import gui.factories.prototypes.ObjectPrototype;

/**
 * Class that handles the difficulty of the Enemy Factory
 * @author dimax
 *
 */
public abstract class DifficultyState {
	
	protected EnemyFactory factory;
	protected Random r;
	protected int typeEnemy;
	
	protected DifficultyState(EnemyFactory ef) {
		factory = ef;
		r = new Random();
		typeEnemy = r.nextInt(100);
	}
	
	/**
	 * Does this?
	 */
	public abstract void doThis();
	
	/**
	 * Creates the enemy
	 * @param le list of die-easy enemies
	 * @param lm list of die-medium enemies
	 * @param lh list of die-hard enemies
	 * @return the Prototype of a randomly selected enemy
	 */
	public abstract ObjectPrototype createEnemy(ArrayList<ObjectPrototype> le , ArrayList<ObjectPrototype> lm , ArrayList<ObjectPrototype> lh);
	  
}

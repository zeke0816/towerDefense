package gui.factories;

import java.util.ArrayList;

import gui.factories.difficulty.DifficultyState;
import gui.factories.difficulty.Easy;
import gui.factories.prototypes.ObjectPrototype;
import gui.factories.prototypes.characters.enemies.AlienPrototype;
import gui.factories.prototypes.characters.enemies.AzulaPrototype;
import gui.factories.prototypes.characters.enemies.DoofPrototype;
import gui.factories.prototypes.characters.enemies.EustacePrototype;
import gui.factories.prototypes.characters.enemies.JirenPrototype;
import gui.factories.prototypes.characters.enemies.SkeletonPrototype;

/**
 * Class that handles the creation of enemies.
 */
public class EnemyFactory {

	protected DifficultyState state;
	protected ArrayList<ObjectPrototype> easyEnemies, mediumEnemies, hardEnemies;
	private static final EnemyFactory instance = new EnemyFactory();
	
	/**
	 * Initializes the Enemy factory with a list of Enemy Prototypes
	 */
	protected EnemyFactory() {
		easyEnemies = new ArrayList<ObjectPrototype>();
		easyEnemies.add(new EustacePrototype());
		easyEnemies.add(new AlienPrototype());
		
		mediumEnemies = new ArrayList<ObjectPrototype>();
		mediumEnemies.add(new DoofPrototype());
		mediumEnemies.add(new SkeletonPrototype());

		hardEnemies = new ArrayList<ObjectPrototype>();
		hardEnemies.add(new AzulaPrototype());
		hardEnemies.add(new JirenPrototype());
		
		state = new Easy(this);
	}
	
	/**
	 * Returns a randomly chosen Droppable Item
	 * @return a droppable item
	 */
	public static EnemyFactory getInstance() {
		return instance;
	}

	/**
	 * Changes the state of this Factory
	 */
	public void upgrade() {
		state.doThis();
	}
	
	/**
	 * Changes the state of this Factory (for reals this time)
	 * @param s the new State 
	 */
	public void changeState(DifficultyState s) {
		state = s;
	}
	
	/**
	 * Returns a randomly chosen Enemy
	 * @return an Enemy prototype
	 */
	public ObjectPrototype createEnemy() {
		return state.createEnemy(easyEnemies, mediumEnemies, hardEnemies);
	}
	
	/**
	 * Increments the life and the strength of the enemy.
	 */
	public void growStats() {
		for(ObjectPrototype ep : easyEnemies) {
			ep.getObject().growStats();
		}
		for(ObjectPrototype ep : mediumEnemies) {
			ep.getObject().growStats();
		}
		for(ObjectPrototype ep : hardEnemies) {
			ep.getObject().growStats();
		}
	}
	
}

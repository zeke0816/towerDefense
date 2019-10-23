package gui.factories;

import java.util.ArrayList;
import java.util.Random;

import gui.factories.enemies.AlienPrototype;
import gui.factories.enemies.AzulaPrototype;
import gui.factories.enemies.DoofPrototype;
import gui.factories.enemies.EnemyPrototype;
import gui.factories.enemies.EustacePrototype;
import gui.factories.enemies.SkeletonPrototype;

/**
 * Class that handles the creation of enemies.
 */
public class EnemyFactory {
	
	protected ArrayList<EnemyPrototype> enemies;
	private static final EnemyFactory instance = new EnemyFactory();
	
	/**
	 * Initializes the Enemy factory with a list of Enemy Prototypes
	 */
	protected EnemyFactory() {
		enemies = new ArrayList<EnemyPrototype>();
		
		enemies.add(new AlienPrototype());
		enemies.add(new AzulaPrototype());
		enemies.add(new DoofPrototype());
		enemies.add(new EustacePrototype());
		enemies.add(new SkeletonPrototype());
	}
	
	/**
	 * Returns a randomly chosen Droppable Item
	 * @return a droppable item
	 */
	public static EnemyFactory getInstance() {
		return instance;
	}
	
	/**
	 * Returns a randomly chosen Enemy
	 * @return an Enemy
	 */
	public EnemyPrototype createEnemy() {
		Random ran = new Random();
		int randomInt = ran.nextInt(5);
		return enemies.get(randomInt);
	}
	
}

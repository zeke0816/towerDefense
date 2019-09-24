package gui.factories;

import java.util.Random;
import characters.Enemy;
import gui.factories.enemies.*;

/**
 * Class that handles the creation of enemies.
 */
public class EnemyFactory {
	protected EnemyPrototype alien;
	protected EnemyPrototype azula;
	protected EnemyPrototype doof;
	protected EnemyPrototype justice;
	protected EnemyPrototype skeleton;
	private static final EnemyFactory instance = new EnemyFactory();
	
	public EnemyFactory() {
		alien = new AlienPrototype();
		azula = new AzulaPrototype();
		doof = new DoofPrototype();
		justice = new JusticePrototype();
		skeleton = new SkeletonPrototype();
	}
	
	public EnemyFactory getInstance() {
		return instance;
	}
	
	public Enemy createEnemy() {
		Random ran = new Random();
		int randomInt = ran.nextInt(7);
		EnemyPrototype enemy = null;
		switch(randomInt) {
			case 0: enemy = alien;
					break;
			case 1: enemy = azula;
					break;
			case 2:	enemy = skeleton;
					break;
			case 3:	enemy = justice;
					break;
			case 4: enemy = doof;
					break;
		}
		return null;
	}
	
}

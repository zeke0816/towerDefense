package game.factories;

import java.util.Random;

import game.factories.characters.enemies.AlienPrototype;
import game.factories.characters.enemies.AzulaPrototype;
import game.factories.characters.enemies.DoofPrototype;
import game.factories.characters.enemies.EnemyPrototype;
import game.factories.characters.enemies.EustacePrototype;
import game.factories.characters.enemies.SkeletonPrototype;

/**
 * Class that handles the creation of enemies.
 */
public class EnemyFactory {
	protected EnemyPrototype alien;
	protected EnemyPrototype azula;
	protected EnemyPrototype doof;
	protected EnemyPrototype eustace;
	protected EnemyPrototype skeleton;
	private static final EnemyFactory instance = new EnemyFactory();
	
	protected EnemyFactory() {
		alien = new AlienPrototype();
		azula = new AzulaPrototype();
		doof = new DoofPrototype();
		eustace = new EustacePrototype();
		skeleton = new SkeletonPrototype();
	}
	
	public static EnemyFactory getInstance() {
		return instance;
	}
	
	public EnemyPrototype createEnemy() {
		Random ran = new Random();
		int randomInt = ran.nextInt(5);
		EnemyPrototype enemy = null;
		switch(randomInt) {
			case 0:
				enemy = alien;
				break;
			case 1:
				enemy = azula;
				break;
			case 2:
				enemy = skeleton;
				break;
			case 3:
				enemy = eustace;
				break;
			case 4:
				enemy = doof;
				break;
		}
		return enemy;
	}
	
}

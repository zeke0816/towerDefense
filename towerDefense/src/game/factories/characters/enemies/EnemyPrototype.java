package game.factories.characters.enemies;

import game.objects.characters.Enemy;

/**
 * Class to represent and save information about a Enemy in the GUI
 */
public abstract class EnemyPrototype {
	protected String id;
	protected String name;
	protected boolean playsSound;
	protected Enemy enemy;
	
	protected EnemyPrototype() {
		
	}
	
	/**
	 * Clones and gets the Enemy to be 
	 * @return the Enemy
	 */
	public Enemy getEnemy() {
		return enemy.clone();
	}
	
	/**
	 * Gets the ID string for media identification
	 * @return the ID of the enemy interface
	 */
	public String getID() {
		return id;
	}

	/**
	 * Gets the display name of the enemy
	 * @return the display name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Tells whether this enemy plays sounds or not
	 * @return true if it plays sounds, false if it does not
	 */
	public boolean playsSound() {
		return playsSound;
	}

}

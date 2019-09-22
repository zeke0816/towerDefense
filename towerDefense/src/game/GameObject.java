package game;

/**
 * Abstract class that helps define the objects in the game
 * @author zeke0816
 *
 */
public abstract class GameObject {
	
	protected int life;
	
	protected GameObject() {
		
	}
	
	protected GameObject(GameObject target) {
		if(target != null) {
			life = target.getLife();
		}
	}
	
	/**
	 * Gets the life of the object
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

}

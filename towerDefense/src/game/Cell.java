package game;

/**
 * Class that represents a position in the arena
 * @author zeke0816
 *
 */
public class Cell {
	
	protected GameObject object;
	
	/**
	 * Initializes an empty cell
	 */
	public Cell() {
		object = null;
	}
	
	/**
	 * Tells whether the cell is taken or not
	 * @return true if taken, false if not taken.
	 */
	public boolean isTaken() {
		return object != null;
	}
	
	/**
	 * Gets the object that is taking this cell
	 * @return the object in the cell
	 */
	public GameObject getObject() {
		return object;
	}
	
	/**
	 * Sets an object to take this cell
	 * @param o the object to put in
	 */
	public void setObject(GameObject o) {
		object = o;
	}
	
	/**
	 * Frees a cell from its object
	 */
	public void free() {
		System.out.println("Freed the cell.");
		object = null;
		if(isTaken()) {
			System.out.println("But it is still taken.");
		}
	}

}

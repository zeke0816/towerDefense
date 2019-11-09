package game;

import game.objects.GameObject;

/**
 * Class that represents a position in the arena
 * @author zeke0816
 *
 */
public class Cell {
	
	protected GameObject object;
	protected boolean canTakeDoubleObjects;
	
	/**
	 * Initializes an empty cell
	 */
	public Cell() {
		object = null;
		canTakeDoubleObjects = true;
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
	 * Tells whether this cell can take a double object
	 * @return true if it can, false if not
	 */
	public boolean canTakeDoubleObjects() {
		return canTakeDoubleObjects;
	}
	
	/**
	 * Sets an object to take this cell
	 * @param o the object to put in
	 */
	public void setObject(GameObject o) {
		object = o;
	}
	
	/**
	 * Sets the ability to take double objects
	 * @param value true if it can, false if not
	 */
	public void takeDoubleObjects(boolean value) {
		canTakeDoubleObjects = value;
	}
	
	/**
	 * Frees a cell from its object
	 */
	public void free() {
		object = null;
	}

}

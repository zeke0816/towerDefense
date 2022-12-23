package game;

import java.util.HashMap;

import exceptions.UnavailableObjectException;
/**
 * Class that models an inventory or warehouse
 * @author zeke0816
 *
 */
public class Inventory {
	
	private HashMap<String, Integer> inventory;
	
	/**
	 * Initializes an empty inventory
	 */
	public Inventory() {
		inventory = new HashMap<String, Integer>();
	}
	
	/**
	 * Removes all the elements from the inventory
	 */
	public void flush() {
		inventory.clear();
	}
	
	/**
	 * Adds an element to the inventory
	 * @param id the ID of the element
	 */
	public void add(String id) {
		if(inventory.get(id) == null) {
			inventory.put(id, 0);
		}
		inventory.put(id, inventory.get(id)+1);
	}
	
	/**
	 * Checks whether an element is in the inventory or not, given its ID
	 * @param id the ID of the element
	 * @return true if it is available, false if not
	 */
	public boolean available(String id) {
		Integer num = inventory.get(id);
		if(num == null) {
			num = 0;
			inventory.put(id, 0);
		}
		return num > 0;
	}
	
	/**
	 * Takes an element from the inventory if it is available
	 * @param id the ID of the element
	 * @throws UnavailableObjectException when the element is not available
	 */
	public void take(String id) throws UnavailableObjectException {
		if(inventory.get(id) == null) {
			throw new UnavailableObjectException("This Game Object is not available at the moment.");
		}
		inventory.put(id, inventory.get(id)-1);
	}

}

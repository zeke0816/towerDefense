package game;

import java.util.HashMap;

import exceptions.UnavailableObjectException;

public class Inventory {
	
	private HashMap<String, Integer> inventory;
	
	public Inventory() {
		inventory = new HashMap<String, Integer>();
	}
	
	public void flush() {
		inventory.clear();
	}
	
	public void add(String id) {
		if(inventory.get(id) == null) {
			inventory.put(id, 0);
		}
		inventory.put(id, inventory.get(id)+1);
	}
	
	public boolean available(String id) {
		Integer num = inventory.get(id);
		if(num == null) {
			num = 0;
			inventory.put(id, 0);
		}
		return num > 0;
	}
	
	public void take(String id) throws UnavailableObjectException {
		if(inventory.get(id) == null) {
			throw new UnavailableObjectException("This Game Object is not available at the moment.");
		}
		inventory.put(id, inventory.get(id)-1);
	}

}

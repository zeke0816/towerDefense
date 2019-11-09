package gui.layouts;

import java.util.HashMap;

import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import game.objects.GameObject;
import gui.controls.PlacedItem;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class DroppingLayout extends Layout<StackPane> {

	private HashMap<GameObject, PlacedItem> droppedObjects;
	private static final DroppingLayout instance = new DroppingLayout();
	
	/**
	 * Initializes the layout creating galleries for the Items to be dropped
	 */
	protected DroppingLayout() {
		super();
		
		Map map = Game.getInstance().getMap();
		int height = Map.cellSize * map.getLanes();
		int width = map.getDistance();
		
		layout = new StackPane();
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setMinSize(width, height);
        layout.setMaxSize(width, height);
        layout.setPrefSize(width, height);
        
		droppedObjects = new HashMap<GameObject, PlacedItem>();
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static DroppingLayout getInstance() {
		return instance;
	}
	
	/**
	 * Flushes the entire layout
	 */
	public void flush() {
		for(GameObject object: droppedObjects.keySet()) {
			try {
				removeObject(object);
			} catch (InvalidActionException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Adds an Item onto the arena
	 * @param lane the lane where the Item will be placed
	 * @param distance the Item's distance to the base
	 * @param id the ID of the Item
	 * @param e the Item itself
	 */
	public void addObject(GameObject object) {
		PlacedItem placedItem = new PlacedItem(object.getID(), object);
		layout.getChildren().add(placedItem);
		droppedObjects.put(object, placedItem);
	}
	
	/**
	 * Removes an Item from the given row
	 * @param lane the given row
	 * @throws InvalidActionException when there is no Item at the given row
	 */
	public void removeObject(GameObject object) throws InvalidActionException {
		PlacedItem placedItem = droppedObjects.remove(object);
		if(placedItem == null) {
			throw new InvalidActionException("This Object does not seem to be dropped on the arena.");
		}
		Game.getInstance().getInventory().add(placedItem.getID());
		InventoryLayout.getInstance().updateAvailability();
		layout.getChildren().remove(placedItem);
	}
	
}

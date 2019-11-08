package gui.layouts;

import java.util.HashMap;

import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import game.objects.items.Item;
import gui.controls.PlacedItem;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class DroppingLayout extends Layout<GridPane> {

	private HashMap<Item, PlacedItem> droppedObjects;
	private StackPane[] droppingRows;
	private static final DroppingLayout instance = new DroppingLayout();
	
	/**
	 * Initializes the layout creating galleries for the Items to be dropped
	 */
	protected DroppingLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);

		Map map = Game.getInstance().getMap();
		
		droppingRows = new StackPane[map.getRows()];
		for(int i = 0; i < map.getRows(); i++) {
			droppingRows[i] = new RowLayout();
			layout.add(droppingRows[i], 0, i);
		}
		droppedObjects = new HashMap<Item, PlacedItem>();
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static DroppingLayout getInstance() {
		return instance;
	}
	
	/**
	 * Adds an Item onto the arena
	 * @param row the row where the Item will be placed
	 * @param id the ID of the Item
	 * @param e the Item itself
	 */
	public void addItem(int row, int col, String id, Item item) {
		PlacedItem placedItem = new PlacedItem(row, col, id, item);
		droppingRows[row].getChildren().add(placedItem);
		droppedObjects.put(item, placedItem);
	}
	
	/**
	 * Removes an Item from the given row
	 * @param row the given row
	 * @throws InvalidActionException when there is no Item at the given row
	 */
	public void removeItem(Item item) throws InvalidActionException {
		PlacedItem placedObject = droppedObjects.get(item);
		if(placedObject == null) {
			throw new InvalidActionException("There are no items dropped on this row.");
		}
		Game.getInstance().getInventory().add(placedObject.getID());
		InventoryLayout.getInstance().updateAvailability();
		droppingRows[placedObject.getRow()].getChildren().remove(droppedObjects.remove(item));
	}
	
}

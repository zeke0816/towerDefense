package gui.controls;

import exceptions.InvalidActionException;
import game.objects.items.Item;
import gui.layouts.DroppingLayout;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PlacedItem extends PlacedObject {
	
	protected Item item;
	/**
	 * Listener for warrior placement on a cell
	 */
	private EventHandler<MouseEvent> pickUpItem = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			// TODO: Add to the inventory
			try {
				DroppingLayout.getInstance().removeItem(item);
			} catch (InvalidActionException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	public PlacedItem(int r, int c, String id, Item it) {
		super(r, c, id);
		item = it;
		setOnMouseClicked(pickUpItem);
	}

}

package gui.controls;

import exceptions.InvalidActionException;
import game.objects.GameObject;
import gui.layouts.DroppingLayout;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class PlacedItem extends PlacedObject {
	
	protected GameObject object;
	private EventHandler<MouseEvent> pickUpItem = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			try {
				DroppingLayout.getInstance().removeObject(object);
			} catch (InvalidActionException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	public PlacedItem(String id, GameObject obj) {
		super(obj.getLane(), obj.getDistance(), id);
		object = obj;
		setOnMouseClicked(pickUpItem);
		setCursor(Cursor.HAND);
	}

}

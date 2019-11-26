package gui.factories.prototypes;

import exceptions.CannotAffordException;
import exceptions.DatabaseException;
import exceptions.UnavailableObjectException;
import game.Map;
import game.objects.GameObject;
import gui.controls.ObjectButton;
import gui.layouts.InventoryLayout;
import gui.layouts.MapLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;
import gui.layouts.StoreLayout;
import gui.scenes.MainScene;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import media.MediaDatabase;

/**
 * Class that helps define a prototype to create all the Game Objects of the Game with their GUI elements
 * @author zeke0816
 *
 */
public class ObjectPrototype {
	
	protected GameObject object;
	protected Label profileLabel;
	protected Label disabledLabel;
	protected ObjectButton placingButton;
	protected ObjectButton buyingButton;
	protected ObjectButton sellingButton;
	protected ObjectButton buyPlaceButton;
	private EventHandler<MouseEvent> selectObjectListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			ObjectPrototype selectedObject = placingButton.getPrototype();
			GameObject object = selectedObject.getObject();
			try {
				PlacementLayout.getInstance().selectObject(selectedObject);
				Image img = MediaDatabase.getInstance().getImageMedia(object.getID()+"_cursor");
				MainScene.getInstance().setCursorImage(img);
				MapLayout.getInstance().allowPlacement();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the Object.");
			} catch (DatabaseException e) {
				System.out.println("The selected Game Object's graphics could not replace the cursor.");
			}
		}
		
	};
	private EventHandler<MouseEvent> buyObjectListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			ObjectPrototype selectedObject = placingButton.getPrototype();
			try {
				selectedObject.getObject().buy();
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
				StatusLayout.getInstance().updateBudget();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the Game Object.");
			} catch (CannotAffordException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	private EventHandler<MouseEvent> sellObjectListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			ObjectPrototype selectedObject = placingButton.getPrototype();
			try {
				selectedObject.getObject().sell();
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
				StatusLayout.getInstance().updateBudget();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the Game Object.");
			} catch (UnavailableObjectException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	private EventHandler<MouseEvent> buyPlaceObjectListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			ObjectPrototype selectedObject = placingButton.getPrototype();
			GameObject object = selectedObject.getObject();
			try {
				object.buy();
				PlacementLayout.getInstance().selectObject(selectedObject);
				Image img = MediaDatabase.getInstance().getImageMedia(object.getID()+"_cursor");
				MainScene.getInstance().setCursorImage(img);
				MapLayout.getInstance().allowPlacement();
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
				StatusLayout.getInstance().updateBudget();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the Game Object.");
			} catch (DatabaseException e) {
				System.out.println("The selected Game Object's graphics could not replace the cursor.");
			} catch (CannotAffordException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	/**
	 * Initializes the Object prototype with the profile, disabled profile, and buttons.
	 */
	protected ObjectPrototype() {
		profileLabel = new Label();
		profileLabel.setVisible(true);
		profileLabel.setPrefSize(Map.cellSize, Map.cellSize);
		profileLabel.setBorder(new Border(new BorderStroke(Color.valueOf("#ff8d7f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setMargin(profileLabel, new Insets(10, 5, 5, 5));
        
        disabledLabel = new Label();
        disabledLabel.setVisible(true);
        disabledLabel.setPrefSize(Map.cellSize, Map.cellSize);
        disabledLabel.setBorder(new Border(new BorderStroke(Color.valueOf("#ff8d7f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setMargin(disabledLabel, new Insets(10, 5, 5, 5));
        
		placingButton = new ObjectButton(Map.cellSize, Map.cellSize);
        placingButton.setOnMouseClicked(selectObjectListener);
        GridPane.setMargin(placingButton, new Insets(10, 5, 5, 5));
        
		buyingButton = new ObjectButton(64, 16);
		buyingButton.setOnMouseClicked(buyObjectListener);
		buyingButton.setBorder(null);
		GridPane.setMargin(buyingButton, new Insets(0, 5, 5, 5));
        
		sellingButton = new ObjectButton(64, 16);
		sellingButton.setOnMouseClicked(sellObjectListener);
		sellingButton.setBorder(null);
		GridPane.setMargin(sellingButton, new Insets(0, 5, 5, 5));
        
		buyPlaceButton = new ObjectButton(64, 16);
		buyPlaceButton.setOnMouseClicked(buyPlaceObjectListener);
		buyPlaceButton.setBorder(null);
		GridPane.setMargin(buyPlaceButton, new Insets(0, 5, 5, 5));
        
		try {
			Background b = MediaDatabase.getInstance().getImageBackgroundMedia("buyButton", 64, 16, true, false);
			Background s = MediaDatabase.getInstance().getImageBackgroundMedia("sellButton", 64, 16, true, false);
			Background bP = MediaDatabase.getInstance().getImageBackgroundMedia("buyPlaceButton", 64, 16, true, false);
			buyingButton.setBackground(b);
			sellingButton.setBackground(s);
			buyPlaceButton.setBackground(bP);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Clones and gets the Warrior to be placed
	 * @return the Warrior
	 */
	public GameObject cloneObject() {
		return object.clone();
	}
	
	/**
	 * Gets the Warrior
	 * @return the Warrior
	 */
	public GameObject getObject() {
		return object;
	}

	/**
	 * Gets the profile label to show on screen
	 * @return the label
	 */
	public Label getProfileLabel() {
		return profileLabel;
	}

	/**
	 * Gets the disabled label to show on screen
	 * @return the label
	 */
	public Label getDisabledLabel() {
		return disabledLabel;
	}

	/**
	 * Gets the placing button to show on screen
	 * @return the placing button
	 */
	public ObjectButton getPlacingButton() {
		return placingButton;
	}

	/**
	 * Gets the buying button to show on screen
	 * @return the buying button
	 */
	public ObjectButton getBuyingButton() {
		return buyingButton;
	}

	/**
	 * Gets the selling button to show on screen
	 * @return the selling button
	 */
	public ObjectButton getSellingButton() {
		return sellingButton;
	}

	/**
	 * Gets the buying & placing button to show on screen
	 * @return the buying & placing button
	 */
	public ObjectButton getBuyPlaceButton() {
		return buyPlaceButton;
	}

}

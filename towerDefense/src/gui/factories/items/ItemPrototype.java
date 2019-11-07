package gui.factories.items;

import exceptions.DatabaseException;
import exceptions.CannotAffordException;
import game.Game;
import game.objects.items.Item;
import gui.controls.ItemButton;
import gui.layouts.InventoryLayout;
import gui.layouts.MapLayout;
import gui.layouts.PlacementLayout;
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
 * Class to represent the items of the game.
 */
public abstract class ItemPrototype {
	
	protected String id;
	protected String key;
	protected String name;
	protected Item item;
	protected Label profileLabel;
	protected Label disabledLabel;
	protected boolean playsSound;
	protected ItemButton placingButton;
	protected ItemButton buyingButton;
	protected ItemButton buyPlaceButton;
	protected static final double size = 64;
	private EventHandler<MouseEvent> selectItemListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				ItemPrototype selectedItem = placingButton.getPrototype();
				PlacementLayout.getInstance().selectItem(selectedItem);
				Image img = MediaDatabase.getInstance().getImageMedia(selectedItem.getID()+"cursor");
				MainScene.getInstance().setCursorImage(img);
				MapLayout.getInstance().allowPlacement();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			} catch (DatabaseException e) {
				System.out.println("The selected Warrior's graphics could not replace the cursor.");
			}
		}
		
	};
	private EventHandler<MouseEvent> buyItemListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				Game game = Game.getInstance();
				ItemPrototype selectedItem = buyPlaceButton.getPrototype();
				game.decreaseBudget(selectedItem.getItem().getPrice());
				game.getInventory().add(selectedItem.getID());
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			} catch (CannotAffordException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	private EventHandler<MouseEvent> buyPlaceItemListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				ItemPrototype selectedItem = buyPlaceButton.getPrototype();
				Game game = Game.getInstance();
				game.decreaseBudget(selectedItem.getItem().getPrice());
				game.getInventory().add(selectedItem.getID());
				PlacementLayout.getInstance().selectItem(selectedItem);
				Image img = MediaDatabase.getInstance().getImageMedia(selectedItem.getID()+"cursor");
				MainScene.getInstance().setCursorImage(img);
				MapLayout.getInstance().allowPlacement();
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			} catch (DatabaseException e) {
				System.out.println("The selected Warrior's graphics could not replace the cursor.");
			} catch (CannotAffordException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	/**
	 * Creates an empty Item Interface
	 */
	protected ItemPrototype(){
		profileLabel = new Label();
		profileLabel.setVisible(true);
		profileLabel.setPrefSize(size, size);
		profileLabel.setBorder(new Border(new BorderStroke(Color.valueOf("#ff8d7f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setMargin(profileLabel, new Insets(10, 5, 5, 5));
        
        disabledLabel = new Label();
        disabledLabel.setVisible(true);
        disabledLabel.setPrefSize(size, size);
        disabledLabel.setBorder(new Border(new BorderStroke(Color.valueOf("#ff8d7f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setMargin(disabledLabel, new Insets(10, 5, 5, 5));
        
		placingButton = new ItemButton(size, size);
        placingButton.setOnMouseClicked(selectItemListener);
        GridPane.setMargin(placingButton, new Insets(10, 5, 5, 5));
        
		buyingButton = new ItemButton(64, 16);
		buyingButton.setOnMouseClicked(buyItemListener);
		buyingButton.setBorder(null);
		GridPane.setMargin(buyingButton, new Insets(0, 5, 5, 5));
        
		buyPlaceButton = new ItemButton(64, 16);
		buyPlaceButton.setOnMouseClicked(buyPlaceItemListener);
		buyPlaceButton.setBorder(null);
		GridPane.setMargin(buyPlaceButton, new Insets(0, 5, 5, 5));
        
		try {
			Background b = MediaDatabase.getInstance().getImageBackgroundMedia("buyButton", 64, 16, true, false);
			Background bP = MediaDatabase.getInstance().getImageBackgroundMedia("buyPlaceButton", 64, 16, true, false);
			buyingButton.setBackground(b);
			buyPlaceButton.setBackground(bP);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Clones and gets the Item to be placed
	 * @return the Item
	 */
	public Item cloneItem() {
		return item.clone();
	}
	
	/**
	 * Gets the Item
	 * @return the Item
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Gets the ID string for media identification
	 * @return the ID of the item interface
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Gets the Key string for media identification
	 * @return the Key of the item interface
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the display name of the item
	 * @return the display name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Tells whether this item plays sounds or not
	 * @return true if it plays sounds, false if it does not
	 */
	public boolean playsSound() {
		return playsSound;
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
	public ItemButton getPlacingButton() {
		return placingButton;
	}

	/**
	 * Gets the buying button to show on screen
	 * @return the buying button
	 */
	public ItemButton getBuyingButton() {
		return buyingButton;
	}

	/**
	 * Gets the buying & placing button to show on screen
	 * @return the buying & placing button
	 */
	public ItemButton getBuyPlaceButton() {
		return buyPlaceButton;
	}
	
}

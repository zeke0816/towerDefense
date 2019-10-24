package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.Item;
import gui.controls.ItemButton;
import gui.layouts.MapLayout;
import gui.layouts.PlacementLayout;
import gui.scenes.MainScene;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import media.databases.MediaDatabase;

/**
 * Class to represent the items of the game.
 */
public abstract class ItemPrototype {
	
	protected String id;
	protected String key;
	protected String name;
	protected Item item;
	protected Label nameLabel;
	protected Label priceLabel;
	protected boolean playsSound;
	protected ItemButton button;
	protected static final double size = 100;
	private EventHandler<MouseEvent> selectItemListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				ItemButton button = (ItemButton) event.getSource();
				ItemPrototype selectedItem = button.getItem();
				PlacementLayout.getInstance().selectItem(selectedItem);
				try {
					Image img = MediaDatabase.getInstance().getImageMedia(selectedItem.getID()+"cursor");
					MainScene.getInstance().setCursorImage(img);
					MapLayout.getInstance().allowPlacement();
				} catch (DatabaseException e) {
					System.out.println("The selected Warrior's graphics could not replace the cursor.");
				}
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			}
		}
		
	};
	
	/**
	 * Creates an empty Item Interface
	 */
	protected ItemPrototype(){
		nameLabel = new Label();
		nameLabel.setVisible(true);
		nameLabel.setAlignment(Pos.CENTER);
		nameLabel.setFont(new Font("Cambria", 20));
        GridPane.setHalignment(nameLabel, HPos.CENTER);
        
		priceLabel = new Label();
		priceLabel.setVisible(true);
		priceLabel.setAlignment(Pos.CENTER);
		priceLabel.setFont(new Font("Cambria", 20));
        GridPane.setHalignment(priceLabel, HPos.CENTER);
        
		button = new ItemButton();
		button.setVisible(true);
        button.setPrefSize(size, size);
        button.setOnMouseClicked(selectItemListener);
	}
	
	/**
	 * Clones and gets the Warrior to be 
	 * @return the Warrior
	 */
	public Item getItem() {
		return item.clone();
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
	 * Gets the name label to show on screen
	 * @return the label
	 */
	public Label getNameLabel() {
		return nameLabel;
	}

	/**
	 * Gets the price label to show on screen
	 * @return the label
	 */
	public Label getPriceLabel() {
		return priceLabel;
	}

	/**
	 * Gets the button to show on screen
	 * @return the button
	 */
	public ItemButton getButton() {
		return button;
	}
	
}

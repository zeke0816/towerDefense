package game.factories.characters.warriors;

import exceptions.DatabaseException;
import game.objects.characters.Warrior;
import gui.controls.WarriorButton;
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
 * Class to represent and save information about a Warrior in the GUI
 * @author zeke0816
 *
 */
public abstract class WarriorPrototype {
	
	protected String id;
	protected String name;
	protected Warrior warrior;
	protected Label nameLabel;
	protected Label priceLabel;
	protected boolean playsSound;
	protected WarriorButton button;
	protected static final double size = 100;
	private EventHandler<MouseEvent> selectWarriorListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				WarriorButton button = (WarriorButton) event.getSource();
				WarriorPrototype selectedWarrior = button.getWarrior();
				PlacementLayout.getInstance().selectWarrior(selectedWarrior);
				try {
					Image img = MediaDatabase.getInstance().getImageMedia(selectedWarrior.getID()+"cursor");
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
	 * Creates an empty Warrior Interface
	 */
	protected WarriorPrototype() {
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
        
		button = new WarriorButton();
		button.setVisible(true);
        button.setPrefSize(size, size);
        button.setOnMouseClicked(selectWarriorListener);
	}
	
	/**
	 * Clones and gets the Warrior to be 
	 * @return the Warrior
	 */
	public Warrior getWarrior() {
		return warrior.clone();
	}
	
	/**
	 * Gets the ID string for media identification
	 * @return the ID of the warrior interface
	 */
	public String getID() {
		return id;
	}

	/**
	 * Gets the display name of the warrior
	 * @return the display name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Tells whether this warrior plays sounds or not
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
	public WarriorButton getButton() {
		return button;
	}

}

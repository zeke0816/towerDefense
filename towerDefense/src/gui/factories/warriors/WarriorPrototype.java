package gui.factories.warriors;

import exceptions.DatabaseException;
import game.characters.Warrior;
import gui.controls.WarriorButton;
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
	protected boolean playsSound;
	protected Label label;
	protected WarriorButton button;
	protected Warrior warrior;
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
		label = new Label();
		label.setVisible(true);
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font("Cambria", 20));
        GridPane.setHalignment(label, HPos.CENTER);
        
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
	 * Gets the label to show on screen
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * Gets the button to show on screen
	 * @return the button
	 */
	public WarriorButton getButton() {
		return button;
	}

}

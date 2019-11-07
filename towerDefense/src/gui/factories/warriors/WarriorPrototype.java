package gui.factories.warriors;

import exceptions.DatabaseException;
import exceptions.NotEnoughBudgetException;
import game.Game;
import game.objects.characters.warriors.Warrior;
import gui.controls.WarriorButton;
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
	protected Label profileLabel;
	protected Label disabledLabel;
	protected boolean playsSound;
	protected WarriorButton placingButton;
	protected WarriorButton buyingButton;
	protected WarriorButton buyPlaceButton;
	protected static final double size = 64;
	private EventHandler<MouseEvent> selectWarriorListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				WarriorPrototype selectedWarrior = placingButton.getPrototype();
				PlacementLayout.getInstance().selectWarrior(selectedWarrior);
				Image img = MediaDatabase.getInstance().getImageMedia(selectedWarrior.getID()+"cursor");
				MainScene.getInstance().setCursorImage(img);
				MapLayout.getInstance().allowPlacement();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			} catch (DatabaseException e) {
				System.out.println("The selected Warrior's graphics could not replace the cursor.");
			}
		}
		
	};
	private EventHandler<MouseEvent> buyWarriorListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				Game game = Game.getInstance();
				WarriorPrototype selectedWarrior = buyPlaceButton.getPrototype();
				game.decreaseBudget(selectedWarrior.getWarrior().getPrice());
				game.getInventory().add(selectedWarrior.getID());
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
				StatusLayout.getInstance().updateBudget();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			} catch (NotEnoughBudgetException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	private EventHandler<MouseEvent> buyPlaceWarriorListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				WarriorPrototype selectedWarrior = buyPlaceButton.getPrototype();
				Game game = Game.getInstance();
				game.decreaseBudget(selectedWarrior.getWarrior().getPrice());
				game.getInventory().add(selectedWarrior.getID());
				PlacementLayout.getInstance().selectWarrior(selectedWarrior);
				Image img = MediaDatabase.getInstance().getImageMedia(selectedWarrior.getID()+"cursor");
				MainScene.getInstance().setCursorImage(img);
				MapLayout.getInstance().allowPlacement();
				InventoryLayout.getInstance().updateAvailability();
				StoreLayout.getInstance().updateAvailability();
				StatusLayout.getInstance().updateBudget();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			} catch (DatabaseException e) {
				System.out.println("The selected Warrior's graphics could not replace the cursor.");
			} catch (NotEnoughBudgetException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	/**
	 * Creates an empty Warrior Interface
	 */
	protected WarriorPrototype() {
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
        
		placingButton = new WarriorButton(size, size);
        placingButton.setOnMouseClicked(selectWarriorListener);
        GridPane.setMargin(placingButton, new Insets(10, 5, 5, 5));
        
		buyingButton = new WarriorButton(64, 16);
		buyingButton.setOnMouseClicked(buyWarriorListener);
		buyingButton.setBorder(null);
		GridPane.setMargin(buyingButton, new Insets(0, 5, 5, 5));
        
		buyPlaceButton = new WarriorButton(64, 16);
		buyPlaceButton.setOnMouseClicked(buyPlaceWarriorListener);
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
	 * Clones and gets the Warrior to be placed
	 * @return the Warrior
	 */
	public Warrior cloneWarrior() {
		return warrior.clone();
	}
	
	/**
	 * Gets the Warrior
	 * @return the Warrior
	 */
	public Warrior getWarrior() {
		return warrior;
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
	public WarriorButton getPlacingButton() {
		return placingButton;
	}

	/**
	 * Gets the buying button to show on screen
	 * @return the buying button
	 */
	public WarriorButton getBuyingButton() {
		return buyingButton;
	}

	/**
	 * Gets the buying & placing button to show on screen
	 * @return the buying & placing button
	 */
	public WarriorButton getBuyPlaceButton() {
		return buyPlaceButton;
	}

}

package gui.layouts;

import exceptions.DatabaseException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import media.databases.MediaDatabase;

public class ButtonLayout extends Layout<StackPane> {
	
	private static final ButtonLayout instance = new ButtonLayout();
	private Button store;
	private Button inventory;
	
	public ButtonLayout() {
		super();

		layout = new StackPane();
		layout.setAlignment(Pos.BOTTOM_LEFT);
		
		store = new Button();
		store.setPrefSize(140, 20);
		try {
			store.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("store", 400, 80, false, true));
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		
		inventory = new Button();
		inventory.setPrefSize(140, 20);
		try {
			inventory.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("inventory", 400, 80, false, true));
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
		inventory.setTranslateX(120);
	}

	/**
	 * Brings the Store up front
	 */
	public void allowBuying() {
		layout.getChildren().clear();
		layout.getChildren().addAll(inventory, store);
	}

	/**
	 * Brings the Inventory up front
	 */
	public void allowPlacing() {
		layout.getChildren().clear();
		layout.getChildren().addAll(store, inventory);
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static ButtonLayout getInstance() {
		return instance;
	}

}

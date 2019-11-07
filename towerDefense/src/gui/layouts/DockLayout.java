package gui.layouts;

import javafx.scene.layout.BorderPane;

public class DockLayout extends Layout<BorderPane> {
	
	private static final DockLayout instance = new DockLayout();
	private boolean buying;

	protected DockLayout() {
		super();
		
		layout = new BorderPane();

		layout.setTop(ButtonLayout.getInstance().getLayout());
		allowBuying();
	}
	
	/**
	 * Brings the Store up front
	 */
	public void allowBuying() {
		buying = true;
		ButtonLayout.getInstance().allowBuying();
		layout.setBottom(StoreLayout.getInstance().getLayout());
	}

	/**
	 * Brings the Inventory up front
	 */
	public void allowPlacing() {
		buying = false;
		ButtonLayout.getInstance().allowPlacing();
		layout.setBottom(InventoryLayout.getInstance().getLayout());
	}
	
	/**
	 * 
	 */
	public void toggleInventory() {
		if(buying) {
			allowPlacing();
		} else {
			allowBuying();
		}
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static DockLayout getInstance() {
		return instance;
	}

}

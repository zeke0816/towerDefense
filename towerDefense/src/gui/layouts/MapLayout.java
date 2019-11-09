package gui.layouts;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Class that manages the map and its layers
 * @author zeke0816
 *
 */
public class MapLayout extends Layout<StackPane> {

	private static final MapLayout instance = new MapLayout();

	protected MapLayout() {
		super();
		layout = new StackPane();
        layout.setAlignment(Pos.CENTER);
        allowPicking();
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MapLayout getInstance() {
		return instance;
	}
	
	/**
	 * Allows the user to place Game Objects onto the arena, disabling picking up dropped Items.
	 */
	public void allowPlacement() {
		layout.getChildren().clear();
		StackPane movementLayout = MovementLayout.getInstance().getLayout();
		GridPane placementLayout = PlacementLayout.getInstance().getLayout();
		StackPane droppingLayout = DroppingLayout.getInstance().getLayout();
		layout.getChildren().addAll(movementLayout, droppingLayout, placementLayout);
	}
	
	/**
	 * Allows the user to pick up dropped Items from the arena, disabling placing Game Objects.
	 */
	public void allowPicking() {
		layout.getChildren().clear();
		StackPane movementLayout = MovementLayout.getInstance().getLayout();
		GridPane placementLayout = PlacementLayout.getInstance().getLayout();
		StackPane droppingLayout = DroppingLayout.getInstance().getLayout();
		layout.getChildren().addAll(movementLayout, placementLayout, droppingLayout);
	}

}
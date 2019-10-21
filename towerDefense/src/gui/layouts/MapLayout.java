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
		
		GridPane movementLayout = MovementLayout.getInstance().getLayout();
		GridPane placementLayout = PlacementLayout.getInstance().getLayout();
		layout.getChildren().addAll(movementLayout, placementLayout);
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MapLayout getInstance() {
		return instance;
	}

}
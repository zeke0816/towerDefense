package gui.layouts;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Class that manages the map and its layers
 * @author zeke0816
 *
 */
public class MapInterface extends LayoutInterface<StackPane> {

	private static final MapInterface instance = new MapInterface();

	protected MapInterface() {
		super();
		layout = new StackPane();
        layout.setAlignment(Pos.CENTER);
		
		GridPane movementLayout = MovementInterface.getInstance().getLayout();
		GridPane placementLayout = PlacementInterface.getInstance().getLayout();
		layout.getChildren().addAll(movementLayout, placementLayout);
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MapInterface getInstance() {
		return instance;
	}

}
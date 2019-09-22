package gui.layouts;

import game.Game;
import game.Map;
import gui.factories.warriors.PlacedWarrior;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Class that manages the movement of each Character on the Map
 * @author zeke0816
 *
 */
public class MovementInterface extends LayoutInterface<GridPane> {

	protected StackPane[] movementRows;
	private static final MovementInterface instance = new MovementInterface();
	
	/**
	 * Initializes the layout creating galleries for the characters to move around
	 */
	protected MovementInterface() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);

		Map map = Game.getInstance().getMap();
		
		movementRows = new StackPane[map.getRows()];
		for(int i = 0; i < map.getRows(); i++) {
			movementRows[i] = new RowInterface();
			layout.add(movementRows[i], 0, i);
		}
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MovementInterface getInstance() {
		return instance;
	}
	
	public void addWarrior(int row, int col) {
		movementRows[row].getChildren().add(new PlacedWarrior(col));
	}
	
}

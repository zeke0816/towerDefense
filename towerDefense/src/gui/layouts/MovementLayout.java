package gui.layouts;

import java.util.HashMap;

import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import gui.factories.enemies.PlacedEnemy;
import gui.factories.warriors.PlacedWarrior;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Class that manages the movement of each Character on the Map
 * @author zeke0816
 *
 */
public class MovementLayout extends Layout<GridPane> {

	private HashMap<Integer, PlacedEnemy> placedEnemies;
	private StackPane[] movementRows;
	private static final MovementLayout instance = new MovementLayout();
	
	/**
	 * Initializes the layout creating galleries for the characters to move around
	 */
	protected MovementLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);

		Map map = Game.getInstance().getMap();
		
		movementRows = new StackPane[map.getRows()];
		for(int i = 0; i < map.getRows(); i++) {
			movementRows[i] = new RowLayout();
			layout.add(movementRows[i], 0, i);
		}
		placedEnemies = new HashMap<Integer, PlacedEnemy>();
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MovementLayout getInstance() {
		return instance;
	}
	
	public void addWarrior(int row, int col) {
		movementRows[row].getChildren().add(new PlacedWarrior(col));
	}
	
	public void addEnemy(int row, String id) {
		PlacedEnemy placedEnemy = new PlacedEnemy(id);
		movementRows[row].getChildren().add(placedEnemy);
		placedEnemies.put(row, placedEnemy);
	}
	
	public void removeEnemy(int row) throws InvalidActionException {
		if(placedEnemies.isEmpty()) {
			throw new InvalidActionException("There are no enemies placed on this row.");
		}
		movementRows[row].getChildren().remove(placedEnemies.remove(row));
	}
	
}

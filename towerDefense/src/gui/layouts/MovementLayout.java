package gui.layouts;

import java.util.HashMap;

import characters.Enemy;
import exceptions.CellTakenException;
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
	
	/**
	 * Adds a Warrior onto the arena
	 * @param row the row where the Warrior will be placed
	 * @param col the column where the Warrior will be placed
	 */
	public void addWarrior(int row, int col) {
		movementRows[row].getChildren().add(new PlacedWarrior(col));
	}
	
	/**
	 * Adds an Enemy onto the arena
	 * @param row the row where the Enemy will be placed
	 * @param id the ID of the Enemy
	 * @param e the Enemy itself
	 */
	public void addEnemy(int row, String id, Enemy e) {
		PlacedEnemy placedEnemy = new PlacedEnemy(id, e);
		movementRows[row].getChildren().add(placedEnemy);
		placedEnemies.put(row, placedEnemy);
	}
	
	/**
	 * Removes an enemy from the given row
	 * @param row the given row
	 * @throws InvalidActionException when there is no Wnemy at the given row
	 */
	public void removeEnemy(int row) throws InvalidActionException {
		if(placedEnemies.get(row) == null) {
			throw new InvalidActionException("There are no enemies placed on this row.");
		}
		movementRows[row].getChildren().remove(placedEnemies.remove(row));
	}
	
	/**
	 * Moves all the Enemies on the arena
	 * @throws InvalidActionException when there are no Enemies on the arena
	 * @throws CellTakenException when an Enemy was trying to take a cell that had already been taken
	 */
	public void moveEnemies() throws InvalidActionException, CellTakenException {
		for(PlacedEnemy enemy: placedEnemies.values()) {
			enemy.advance();
		}
	}
	
}

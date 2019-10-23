package gui.layouts;

import java.util.HashMap;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.GameObject;
import game.Map;
import game.objects.characters.Enemy;
import game.objects.characters.Warrior;
import game.objects.items.Item;
import gui.attacks.EnergyBlast;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Class that manages the movement of each Character on the Map
 * @author zeke0816
 *
 */
public class MovementLayout extends Layout<GridPane> {

	private HashMap<GameObject, PlacedObject> placedObjects;
	private StackPane[] movementRows;
	private static final MovementLayout instance = new MovementLayout();
	
	/**
	 * Initializes the layout creating galleries for the Game Objects to move around
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
		placedObjects = new HashMap<GameObject, PlacedObject>();
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MovementLayout getInstance() {
		return instance;
	}
	
	/**
	 * Adds a Game Object onto the arena
	 * @param row the row where the Game Object will be placed
	 * @param id the ID of the Game Object
	 * @param e the Game Object itself
	 */
	public void addObject(int row, int col, String id, GameObject object) {
		PlacedObject placedObject = new PlacedObject(row, col, id);
		movementRows[row].getChildren().add(placedObject);
		placedObjects.put(object, placedObject);
	}
	
	/**
	 * Removes a Game Object from the given row
	 * @param row the given row
	 * @throws InvalidActionException when there is no Game Object at the given row
	 */
	public void removeObject(GameObject object) throws InvalidActionException {
		PlacedObject placedObject = placedObjects.get(object);
		if(placedObject == null) {
			throw new InvalidActionException("There are no enemies placed on this row.");
		}
		movementRows[placedObject.getRow()].getChildren().remove(placedObjects.remove(object));
	}
	
	/**
	 * Moves a given Warrior on the arena
	 * @throws InvalidActionException when there are no Warriors on the arena
	 * @throws CellTakenException when a Warrior was trying to take a cell that had already been taken
	 */
	public void moveObject(Warrior object) throws InvalidActionException, CellTakenException {
		PlacedObject placedObject = placedObjects.get(object);
		if(placedObject != null) {
			placedObject.advance(object);
		}
	}
	
	/**
	 * Moves a given Enemy on the arena
	 * @throws InvalidActionException when there are no Enemies on the arena
	 * @throws CellTakenException when a Enemy was trying to take a cell that had already been taken
	 */
	public void moveObject(Enemy object) throws InvalidActionException, CellTakenException {
		PlacedObject placedObject = placedObjects.get(object);
		if(placedObject != null) {
			placedObject.advance(object);
		}
	}
	
	/**
	 * Moves a given Item on the arena
	 * @throws InvalidActionException when there are no Items on the arena
	 * @throws CellTakenException when a Item was trying to take a cell that had already been taken
	 */
	public void moveObject(Item object) throws InvalidActionException, CellTakenException {
		PlacedObject placedObject = placedObjects.get(object);
		if(placedObject != null) {
			placedObject.advance(object);
		}
	}
	
	/**
	 * Graphically show how a Game Object attacks another Game Object
	 * @param attacker the attacking Game Object
	 * @param blown the attacked Game Object
	 */
	public void attackObject(GameObject attacker, GameObject blownup) {
		PlacedObject placedAttacker = placedObjects.get(attacker);
		PlacedObject placedBlownUp = placedObjects.get(blownup);
		EnergyBlast blast = new EnergyBlast(placedAttacker, placedBlownUp);
		blast.shoot(blownup);
	}
	
	/**
	 * Adds a Blast onto the arena
	 * @param row the row where the Blast will be placed
	 * @param blast the Blast itself
	 */
	public void addBlast(int row, Label blast) {
		movementRows[row].getChildren().add(blast);
	}
	
	/**
	 * Removes a Blast from the given row
	 * @param row the row where the Blast is placed
	 * @param blast the Blast itself
	 */
	public void removeBlast(int row, Label blast) {
		movementRows[row].getChildren().remove(blast);
	}
	
}

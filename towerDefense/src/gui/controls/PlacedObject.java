package gui.controls;

import exceptions.CellTakenException;
import exceptions.DatabaseException;
import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
import javafx.scene.control.Label;
import media.MediaDatabase;

/**
 * Class that helps understand the movement of an Enemy on the arena and communicate such information with the logic of the game
 * @author zeke0816
 *
 */
public class PlacedObject extends Label {
	
	private int lane;
	private int distance;
	private String id;

	/**
	 * Initializes a Placed Object with its ID, coordinates, and 0 advanced pixels
	 * @param l the lane
	 * @param d the distance to the base
	 * @param id the ID of the Game Object
	 */
	public PlacedObject(int l, int d, String i) {
		super();
		
		lane = l;
		distance = d;
		id = i;
		
		setMinSize(Map.cellSize, Map.cellSize);
		setPrefSize(Map.cellSize, Map.cellSize);
		setMaxSize(Map.cellSize, Map.cellSize * 2);

		setTranslateY(Map.cellSize * lane);
		setTranslateX(distance);
		// System.out.println("Y: "+getTranslateY());
		// System.out.println("X: "+getTranslateX());
		
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, Map.cellSize, Map.cellSize, true, false));
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Gets the ID of the object
	 * @return the ID
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Moves a Warrior on the arena
	 * @throws InvalidActionException when there are no Warriors on the arena
	 * @throws CellTakenException when a Warrior was trying to take a cell that had already been taken
	 */
	public void advance(Warrior w) throws InvalidActionException, CellTakenException {
		// Warriors do not move
	}
	
	/**
	 * Moves an Enemy on the arena
	 * @throws InvalidActionException when there are no Enemies on the arena
	 * @throws CellTakenException when an Enemy was trying to take a cell that had already been taken
	 */
	public void advance(Enemy e) throws InvalidActionException, CellTakenException {
		Map map = Game.getInstance().getMap();
		if(map.canAdvance(e)) {
			e.move(); // attempts to move
			if(e.getMovementAttempts() == e.getMovementAttempts()) {
				map.advance(e);
				setTranslateX(getTranslateX() - 1);
				distance--;
				e.resetMovementAttempts();
			}
		}
	}
	
	/**
	 * Moves a Item on the arena
	 * @throws InvalidActionException when there are no Items on the arena
	 * @throws CellTakenException when a Item was trying to take a cell that had already been taken
	 */
	public void advance(Item i) throws InvalidActionException, CellTakenException {
		// Items do not move
	}
	
	/**
	 * Wears off a charm from this Placed Object
	 */
	public void wearCharmOff() {
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, Map.cellSize, Map.cellSize, true, false));
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Applies a charm onto this Placed Object
	 */
	public void applyCharm(String key) {
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id+key, Map.cellSize, Map.cellSize, true, false));
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Gets the lane where the Game Object is located
	 * @return the lane
	 */
	public int getLane() {
		return lane;
	}
	
	/**
	 * Gets the Game Object's distance to the base
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

}

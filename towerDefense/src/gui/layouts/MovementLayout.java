package gui.layouts;

import java.util.HashMap;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
import gui.attacks.EnergyBlast;
import gui.attacks.ExplosionBlast;
import gui.controls.PlacedObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Class that manages the movement of each Character on the Map
 * @author zeke0816
 *
 */
public class MovementLayout extends Layout<StackPane> {

	private HashMap<GameObject, PlacedObject> placedObjects;
	private static final MovementLayout instance = new MovementLayout();
	
	/**
	 * Initializes the layout creating galleries for the Game Objects to move around
	 */
	protected MovementLayout() {
		super();

		Map map = Game.getInstance().getMap();
		int width = map.getDistance();
		int height = map.getLanes() * Map.cellSize;
		
		layout = new StackPane();
		layout.setAlignment(Pos.TOP_LEFT);
		layout.setMinSize(width, height);
		layout.setMaxSize(width, height);
		layout.setPrefSize(width, height);
		
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
	 * Flushes the entire layout
	 */
	public void flush() {
		for(GameObject object: placedObjects.keySet()) {
			removeObject(object);
		}
	}
	
	/**
	 * Adds a Game Object onto the arena
	 * @param l the lane where the Game Object will be placed
	 * @param d the the Game Object's distance to the base
	 * @param id the ID of the Game Object
	 * @param e the Game Object itself
	 */
	public void addObject(GameObject object) {
		PlacedObject placedObject = new PlacedObject(object.getLane(), object.getDistance(), object.getID());
		layout.getChildren().add(placedObject);
		placedObjects.put(object, placedObject);
	}
	
	/**
	 * Removes a Game Object from the given row
	 * @param row the given row
	 * @throws InvalidActionException when there is no Game Object at the given row
	 */
	public void removeObject(GameObject object) {
		PlacedObject placedObject = placedObjects.get(object);
		if(placedObject != null) {
			layout.getChildren().remove(placedObjects.remove(object));
		}
	}
	
	/**
	 * Moves a given Warrior on the arena
	 * @throws InvalidActionException when there are no Warriors on the arena
	 * @throws CellTakenException when a Warrior was trying to take a cell that had already been taken
	 */
	public void moveObject(Warrior object) throws InvalidActionException, CellTakenException {
		// Warriors do not move
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
		// Items do not move
	}
	
	/**
	 * Graphically show how a Game Object attacks another Game Object
	 * @param attacker the attacking Game Object
	 * @param blown the attacked Game Object
	 */
	public void attackObject(GameObject attacker, GameObject blownup) {
		int lane = attacker.getLane();
		if(attacker.getLane() < blownup.getLane()) {
			lane = blownup.getLane();
		}
		EnergyBlast blast = new EnergyBlast(lane, attacker.getDistance(), blownup.getDistance());
		blast.shoot(blownup);
	}
	
	/**
	 * Graphically show an explosion on a cell from an Item
	 * @param item the source Item
	 * @param left the left boundary of the explosion
	 * @param top the top boundary of the explosion
	 * @param scope the distance from the top and the left covered by the explosion
	 */
	public void explodeRegion(Item item, int left, int top, int scope) {
		ExplosionBlast blast = new ExplosionBlast(left, top, scope);
		blast.shoot(item);
	}
	
	/**
	 * Adds a Blast onto the arena
	 * @param blast the Blast
	 */
	public void addBlast(Label blast) {
		layout.getChildren().add(blast);
	}
	
	/**
	 * Removes a Blast from the given row
	 * @param blast the Blast
	 */
	public void removeBlast(Label blast) {
		layout.getChildren().remove(blast);
	}
	
	/**
	 * Wears a Spell off a given Game Object
	 * @param object the affected Game Object
	 */
	public void wearCharmOff(GameObject object) {
		PlacedObject placedObject = placedObjects.get(object);
		placedObject.wearCharmOff();
	}
	
	/**
	 * Applies a Spell onto a given Game Object
	 * @param object the affected Game Object
	 * @param key the charm's GUI identifying key
	 */
	public void applyCharm(GameObject object, String key) {
		PlacedObject placedObject = placedObjects.get(object);
		placedObject.applyCharm(key);
	}
	
}

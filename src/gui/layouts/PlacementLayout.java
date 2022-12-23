package gui.layouts;

import java.util.Random;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.Level;
import game.Map;
import game.Wave;
import game.objects.GameObject;
import game.objects.characters.states.Shielded;
import gui.controls.CellButton;
import gui.factories.EnemyFactory;
import gui.factories.ItemFactory;
import gui.factories.prototypes.ObjectPrototype;
import gui.scenes.MainScene;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import media.sounds.SoundPlayer;

/**
 * Class that manages the placement of each Warrior on the Map
 * @author zeke0816
 *
 */
public class PlacementLayout extends Layout<GridPane> {

	private Map map;
	private ObjectPrototype selectedObject;
	private static final PlacementLayout instance = new PlacementLayout();
	
	/**
	 * Initializes the layout for the buttons that will handle the positioning of the Warriors
	 */
	protected PlacementLayout() {
		super();

		map = Game.getInstance().getMap();
		int height = Map.cellSize * map.getLanes();
		int width = map.getDistance();
		
		layout = new GridPane();
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setMinSize(width, height);
        layout.setMaxSize(width, height);
        layout.setPrefSize(width, height);
        
        selectedObject = null;
        
		int lanes = map.getLanes();
		int cells = map.getDistance() / Map.cellSize;
		for(int i = 0; i < lanes; i++) {
			for(int j = 0; j < cells; j++) {
				CellButton cell = new CellButton(i, j);
				layout.add(cell, j, i);
			}
		}
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static PlacementLayout getInstance() {
		return instance;
	}
	
	/**
	 * Checks whether a Warrior has been selected
	 * @return true if a Warrior is currently selected, false if not
	 */
	public boolean objectSelected() {
		return selectedObject != null;
	}
	
	/**
	 * Gets the selected Warrior
	 * @return the currently selected Warrior
	 */
	public ObjectPrototype getSelectedObject() {
		return selectedObject;
	}
	
	/**
	 * Selects the Warrior to be placed in the map
	 * @param w the Warrior
	 */
	public void selectObject(ObjectPrototype prototype) {
		selectedObject = prototype;
	}
	
	/**
	 * Deselects the Object to be placed in the map
	 */
	public void deselectObject() {
		selectedObject = null;
		MainScene.getInstance().resetCursorImage();
	}
	
	/**
	 * Kills a given object and removes it from the GUI and the logic
	 * @param object the Object that is dead
	 */
	public void killObject(GameObject object) {
		if(object.drops()) {
			dropItem(object.getLane(), object.getDistance());
		}
		MovementLayout.getInstance().removeObject(object);
		StatusLayout.getInstance().updateScore();
		StatusLayout.getInstance().updateBudget();
		StoreLayout.getInstance().updateAvailability();
	}
	
	/**
	 * Randomly spawns an Item with a .1% chance of happening
	 */
	public void spawnItem() {
		Random r = new Random();
		int newEnemyChooser = r.nextInt(1000); // .1% chance
		if(newEnemyChooser == 0) {
			int lane;
			int distance;
			do {
				lane = r.nextInt(map.getLanes());
				distance = r.nextInt(map.getDistance());
			} while(distance < (map.getDistance() * Map.limitFactor) && map.getCell(lane, distance).isTaken());
			try {
				GameObject item = ItemFactory.getInstance().createRandomItem().cloneObject();
				item.setLane(lane);
				item.setDistance(distance);
				map.takeCell(item);
				MovementLayout.getInstance().addObject(item);
				if(item.playsSound()) {
					SoundPlayer.getInstance().play(item.getID());
				}
			} catch(CellTakenException | InvalidActionException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Drops a random Droppable Item in the given coordinates
	 */
	public void dropItem(int lane, int distance) {
		GameObject item = ItemFactory.getInstance().createDroppableItem().cloneObject();
		item.setLane(lane);
		item.setDistance(distance);
		DroppingLayout.getInstance().addObject(item);
		if(item.playsSound()) {
			SoundPlayer.getInstance().play(item.getID());
		}
	}
	
	/**
	 * Randomly spawns an Enemy with a 2% chance of happening
	 */
	public void spawnEnemy() {
		Level level = Game.getInstance().getLevel();
		Wave wave = level.getWave();
		
		if(wave.isChanging()) {
			EnemyFactory.getInstance().growStats();
			wave.stopChanging();
		}
		if(level.isChanging()) {
			EnemyFactory.getInstance().upgrade();
			level.stopChanging();
		}
		
		if(wave.spawns() < wave.spawnLimit()) {
			Random r = new Random();
			int newEnemyChooser = r.nextInt(50); // 2% chance
			if(newEnemyChooser == 0) {
				int lane;
				int distance = (map.getDistance() - Map.cellSize);
				int maxAttempts = 21; // arbitrary number
				int currentAttempts = 0;
				do {
					lane = r.nextInt(map.getLanes());
					currentAttempts++;
				} while(currentAttempts < maxAttempts && map.getCell(lane, distance).isTaken());
				try {
					if(currentAttempts == maxAttempts) {
						throw new CellTakenException("All initial cells have been taken. Enemy spawn has been delayed.");
					}
					GameObject enemy = EnemyFactory.getInstance().createEnemy().cloneObject();
					enemy.setLane(lane);
					enemy.setDistance(distance);
					if(r.nextInt(20) == 0) { // 5% chance
						enemy.changeState(new Shielded(enemy));
					}
					map.takeCell(enemy);
					MovementLayout.getInstance().addObject(enemy);
					if(enemy.playsSound()) {
						SoundPlayer.getInstance().play(enemy.getID());
					}
					wave.spawn();
				} catch(CellTakenException | InvalidActionException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}

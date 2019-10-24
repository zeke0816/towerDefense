package gui.layouts;

import java.util.Random;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import exceptions.UnselectedObjectException;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
import game.visitors.PlacementVisitor;
import gui.controls.CellButton;
import gui.factories.EnemyFactory;
import gui.factories.ItemFactory;
import gui.factories.enemies.EnemyPrototype;
import gui.factories.items.ItemPrototype;
import gui.factories.warriors.WarriorPrototype;
import gui.scenes.MainScene;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import media.sounds.SoundPlayer;

/**
 * Class that manages the placement of each Warrior on the Map
 * @author zeke0816
 *
 */
public class PlacementLayout extends Layout<GridPane> {

	private Map map;
	private PlacementVisitor placement;
	private ItemPrototype selectedItem;
	private WarriorPrototype selectedWarrior;
	private final static double cellSize = 64;
	private final static double placementLimitRatio = .6;
	private static final PlacementLayout instance = new PlacementLayout();
	
	/**
	 * Initializes the layout for the buttons that will handle the positioning of the Warriors
	 */
	protected PlacementLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        
        selectedItem = null;
        selectedWarrior = null;
        
        placement = new PlacementVisitor();
        
		map = Game.getInstance().getMap();
		double placementLimit = map.getColumns() * placementLimitRatio;
		
		for(int i = 0; i < map.getRows(); i++) {
			for(int j = 0; j < map.getColumns(); j++) {
				CellButton cell = new CellButton();
				cell.setCoordinates(i, j);
				if(j < placementLimit){
					cell.setOnMouseClicked(placementListener);
				}
				cell.setOnMouseEntered(placementAllowanceListener);
				cell.setOnMouseExited(placementDismissedListener);
				layout.add(cell, j, i);
			}
		}
		layout.setOnKeyPressed(enemyPlacementListener);
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static PlacementLayout getInstance() {
		return instance;
	}

	/**
	 * Gets the cell size
	 * @return the only instance of this class
	 */
	public static double getCellSize() {
		return cellSize;
	}
	
	/**
	 * Checks whether a Warrior has been selected
	 * @return true if a Warrior is currently selected, false if not
	 */
	public boolean warriorSelected() {
		return selectedWarrior != null;
	}
	
	/**
	 * Gets the selected Warrior
	 * @return the currently selected Warrior
	 */
	public WarriorPrototype getSelectedWarrior() {
		return selectedWarrior;
	}
	
	/**
	 * Selects the Warrior to be placed in the map
	 * @param w the Warrior
	 */
	public void selectWarrior(WarriorPrototype w) {
		selectedWarrior = w;
	}
	
	/**
	 * Deselects the Warrior to be placed in the map
	 */
	public void deselectWarrior() {
		selectedWarrior = null;
		MainScene.getInstance().resetCursorImage();
	}
	
	/**
	 * Checks whether an Item has been selected
	 * @return true if an Item is currently selected, false if not
	 */
	public boolean itemSelected() {
		return selectedItem != null;
	}
	
	/**
	 * Gets the selected Item
	 * @return the currently selected Item
	 */
	public ItemPrototype getSelectedItem() {
		return selectedItem;
	}
	
	/**
	 * Selects the Item to be placed in the map
	 * @param i the Item
	 */
	public void selectItem(ItemPrototype i) {
		selectedItem = i;
	}
	
	/**
	 * Deselects the Item to be placed in the map
	 */
	public void deselectItem() {
		selectedItem = null;
		MainScene.getInstance().resetCursorImage();
	}
	
	/**
	 * Kills a given object and removes it from the GUI and the logic
	 * @param object the Object that is dead
	 */
	public void killObject(GameObject object) {
		try {
			if(object.drops()) {
				Pair<Integer, Integer> coordinates = Game.getInstance().getMap().getObjectPosition(object);
				dropItem(coordinates.getKey(), coordinates.getValue());
			}
			MovementLayout.getInstance().removeObject(object);
			Game.getInstance().getMap().freeCell(object);
			Game.getInstance().updateScore(object.getPoints());
			StatusLayout.getInstance().updateScore();
		} catch(InvalidActionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Randomly spawns an Item with a 1% chance of happening
	 */
	public void spawnItem() {
		Random r = new Random();
		int newEnemyChooser = r.nextInt(100);
		if(newEnemyChooser == 0) {
			int row;
			int col;
			do {
				row = r.nextInt(map.getRows());
				col = r.nextInt(map.getColumns());
			} while(map.getCell(row, col).isTaken());
			try {
				ItemPrototype itemPrototype = ItemFactory.getInstance().createRandomItem();
				Item item = itemPrototype.getItem();
				map.takeCell(row, col, item);
				MovementLayout.getInstance().addObject(row, col, itemPrototype.getID(), item);
				if(itemPrototype.playsSound()) {
					SoundPlayer.getInstance().play(itemPrototype.getID());
				}
			} catch(CellTakenException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Drops a random Droppable Item in the given coordinates
	 */
	public void dropItem(int row, int col) {
		ItemPrototype itemPrototype = ItemFactory.getInstance().createDroppableItem();
		Item item = itemPrototype.getItem();
		DroppingLayout.getInstance().addItem(row, col, itemPrototype.getID(), item);
		if(itemPrototype.playsSound()) {
			SoundPlayer.getInstance().play(itemPrototype.getID());
		}
	}
	
	/**
	 * Randomly spawns an Enemy with a 20% chance of happening
	 */
	public void spawnEnemy() {
		Random r = new Random();
		int newEnemyChooser = r.nextInt(5);
		// TODO: add the wave limit here. The wave is over when the limit is reached and all enemies have been killed.
		if(newEnemyChooser == 0) {
			int row;
			int col = map.getColumns()-1;
			int maxAttempts = 21;
			int currentAttempts = 0;
			do {
				row = r.nextInt(map.getRows());
				currentAttempts++;
			} while(currentAttempts < maxAttempts && map.getCell(row, col).isTaken());
			try {
				if(currentAttempts == maxAttempts) {
					throw new CellTakenException("All initial cells have been taken. Enemy spawn has been delayed.");
				}
				EnemyPrototype enemyPrototype = EnemyFactory.getInstance().createEnemy();
				Enemy enemy = enemyPrototype.getEnemy();
				map.takeCell(row, map.getColumns()-1, enemy);
				MovementLayout.getInstance().addObject(row, col, enemyPrototype.getID(), enemy);
				if(enemyPrototype.playsSound()) {
					SoundPlayer.getInstance().play(enemyPrototype.getID());
				}
			} catch(CellTakenException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Listener for warrior placement being allowed
	 */
	private EventHandler<MouseEvent> placementAllowanceListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(warriorSelected() || itemSelected()) {
					CellButton cell = (CellButton) event.getSource();
					int row = cell.getX();
					int col = cell.getY();
					
					double placementLimit = map.getColumns() * placementLimitRatio;
					
					if(col < placementLimit && !map.getCell(row, col).isTaken()) {
						cell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9ae39c"), null, null)));
					} else {
						cell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f05959"), null, null)));
					}
				}
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while placing the warrior.");
			}
		}
		
	};
	
	/**
	 * Listener for warrior placement being dismissed
	 */
	private EventHandler<MouseEvent> placementDismissedListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				Button cell = (Button) event.getSource();
				cell.setBackground(null);
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while placing the warrior.");
			}
		}
		
	};
	
	/**
	 * Listener for warrior placement on a cell
	 */
	private EventHandler<MouseEvent> placementListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				CellButton cell = (CellButton) event.getSource();
				int row = cell.getX();
				int col = cell.getY();
				placement.setObject(map.getObjectAt(row, col), row, col);
				if(!warriorSelected() && !itemSelected()) {
					if(!warriorSelected()) {
						throw new UnselectedObjectException("No Warrior has been selected!");
					}
					if(!itemSelected()){
						throw new UnselectedObjectException("No Item has been selected!");
					}
				}
				if(warriorSelected()) {
					Warrior warrior = selectedWarrior.getWarrior();
					warrior.accept(placement);
				}
				if(itemSelected()) {
					Item item = selectedItem.getItem();
					item.accept(placement);
				}
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while placing an Object.");
			} catch(UnselectedObjectException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	/**
	 * Listener for enemy placement on a cell
	 */
	private EventHandler<KeyEvent> enemyPlacementListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			if(key.getCode() == KeyCode.ESCAPE) {
				deselectWarrior();
				deselectItem();
				MapLayout.getInstance().allowPicking();
			}
		}
		
	};

}

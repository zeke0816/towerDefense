package gui.layouts;

import java.util.Random;
import java.util.Stack;

import characters.Enemy;
import characters.Warrior;
import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import exceptions.UnselectedWarriorException;
import game.Game;
import game.Map;
import gui.controls.CellButton;
import gui.factories.EnemyFactory;
import gui.factories.WarriorFactory;
import gui.factories.enemies.EnemyPrototype;
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

	private Stack<Enemy> placedEnemies;
	private WarriorPrototype selectedWarrior;
	private final static double cellSize = 64;
	private static final PlacementLayout instance = new PlacementLayout();
	
	/**
	 * Initializes the layout for the buttons that will handle the positioning of the Warriors
	 */
	protected PlacementLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        
        selectedWarrior = null;
        
		Map map = Game.getInstance().getMap();
		
		double placementLimit = map.getColumns() * .6; // 60% of the arena
		
		for(int i = 0; i < map.getRows(); i++) {
			for(int j = 0; j < map.getColumns(); j++) {
				CellButton cell = new CellButton();
				cell.setCoordinates(i, j);
				if(j < placementLimit){
					cell.setOnMouseClicked(warriorPlacementListener);
					cell.setOnMouseEntered(placementAllowedListener);
				} else {
					cell.setOnMouseEntered(placementNotAllowedListener);
				}
				cell.setOnMouseExited(placementDismissedListener);
				layout.add(cell, j, i);
			}
		}
		layout.setOnKeyPressed(enemyPlacementListener);
		placedEnemies = new Stack<Enemy>();
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
	}
	
	/**
	 * Listener for warrior placement being allowed
	 */
	private EventHandler<MouseEvent> placementAllowedListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(warriorSelected()) {
					Button cell = (Button) event.getSource();
					cell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#9ae39c"), null, null)));
				}
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while placing the warrior.");
			}
		}
		
	};
	
	/**
	 * Listener for warrior placement not being allowed
	 */
	private EventHandler<MouseEvent> placementNotAllowedListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(warriorSelected()) {
					Button cell = (Button) event.getSource();
					cell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#f05959"), null, null)));
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
	private EventHandler<MouseEvent> warriorPlacementListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(!warriorSelected()) {
					throw new UnselectedWarriorException("No Warrior has been selected!");
				}
				CellButton cell = (CellButton) event.getSource();
				cell.setOnMouseEntered(placementNotAllowedListener);
				int row = cell.getX();
				int col = cell.getY();
				Warrior warrior = WarriorFactory.getInstance().createWarrior(selectedWarrior.getID());
				Game.getInstance().getMap().takeCell(row, col, warrior);
				cell.setBackground(null);
				if(selectedWarrior.playsSound()) {
					SoundPlayer.getInstance().play(selectedWarrior.getID());
				}
				MovementLayout.getInstance().addWarrior(row, col);
				// TODO: if there are warriors of the same type available in the inventory, do not reset the cursor nor deselect the warrior
				MainScene.getInstance().resetCursorImage();
				deselectWarrior();
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while placing the warrior.");
			} catch(CellTakenException e) {
				System.out.println(e.getMessage());
			} catch(UnselectedWarriorException e) {
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
			Map map = Game.getInstance().getMap();
			try {
				if(key.getCode() == KeyCode.C) {
					if(placedEnemies.size() == map.getRows()) {
						throw new InvalidActionException("You reached the limit of Enemies on the arena for now.");
					}
					Random r = new Random();
					int row;
					do {
						row = r.nextInt(map.getRows());
					} while(map.getCell(row, map.getColumns()-1).isTaken());
					EnemyPrototype enemyPrototype = EnemyFactory.getInstance().createEnemy();
					Enemy enemy = enemyPrototype.getEnemy();
					map.takeCell(row, map.getColumns()-1, enemy);
					MovementLayout.getInstance().addEnemy(row, enemyPrototype.getID(), enemy);
					placedEnemies.push(enemy);
					if(enemyPrototype.playsSound()) {
						SoundPlayer.getInstance().play(enemyPrototype.getID());
					}
				} else if(key.getCode() == KeyCode.K) {
					if(placedEnemies.isEmpty()) {
						throw new InvalidActionException("There are no more Enemies to kill.");
					}
					Enemy enemyToDestroy = placedEnemies.pop();
					Pair<Integer, Integer> coordinates = Game.getInstance().getMap().freeCell(enemyToDestroy);
					MovementLayout.getInstance().removeEnemy(coordinates.getKey());
					StatusLayout.getInstance().updateScore();
				} else if(key.getCode() == KeyCode.M) {
					MovementLayout.getInstance().moveEnemies();
				}
			} catch(CellTakenException | InvalidActionException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};

}

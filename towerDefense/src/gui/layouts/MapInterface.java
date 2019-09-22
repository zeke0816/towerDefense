package gui.layouts;

import characters.Warrior;
import exceptions.CellTakenException;
import exceptions.UnselectedWarriorException;
import game.Game;
import game.Map;
import gui.controls.CellInterface;
import gui.factories.WarriorFactory;
import gui.factories.warriors.PlacedWarrior;
import gui.factories.warriors.WarriorInterface;
import gui.scenes.MainScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import media.sounds.SoundPlayer;

public class MapInterface extends LayoutInterface<StackPane> {

	protected GridPane placementLayout;
	protected StackPane[] placementRows;
	protected GridPane movementLayout;
	protected WarriorInterface selectedWarrior;
	protected final static int cellSize = 64;
	private static final MapInterface instance = new MapInterface();

	protected MapInterface() {
		super();
		selectedWarrior = null;
		
		layout = new StackPane();
        movementLayout = new GridPane();
        placementLayout = new GridPane();
        
        layout.setAlignment(Pos.CENTER);
        movementLayout.setAlignment(Pos.CENTER);
        placementLayout.setAlignment(Pos.CENTER);

		Map map = Game.getInstance().getMap();
		
		// set the placement limit on the arena for warriors
		double placementLimit = map.getColumns() * .6; // 60% of the arena
		placementRows = new StackPane[map.getRows()];
		
		for(int i = 0; i < map.getRows(); i++) {
			StackPane row = new StackPane();
			row.setAlignment(Pos.CENTER_LEFT);
			row.setPrefHeight(cellSize);
			row.setPrefWidth(cellSize * map.getColumns());
			placementRows[i] = row;
			movementLayout.add(row, 0, i);
			for(int j = 0; j < map.getColumns(); j++) {
				CellInterface cell = new CellInterface();
				cell.setCoordinates(i, j);
				if(j < placementLimit){
					cell.setOnAction(cellListener);
					cell.setOnMouseEntered(placementAllowedListener);
				} else {
					cell.setOnMouseEntered(placementNotAllowedListener);
				}
				cell.setOnMouseExited(placementDismissedListener);
				placementLayout.add(cell, j, i);
			}
		}
		
		layout.getChildren().addAll(movementLayout, placementLayout);
		layout.setAlignment(Pos.CENTER);
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static MapInterface getInstance() {
		return instance;
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
	public WarriorInterface selectedWarrior() {
		return selectedWarrior;
	}
	
	/**
	 * Selects the Warrior to be placed in the map
	 * @param w the Warrior
	 */
	public void selectWarrior(WarriorInterface w) {
		selectedWarrior = w;
	}
	
	/**
	 * Listener for warrior placement being allowed
	 */
	EventHandler<MouseEvent> placementAllowedListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(selectedWarrior != null) {
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
	EventHandler<MouseEvent> placementNotAllowedListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(selectedWarrior != null) {
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
	EventHandler<MouseEvent> placementDismissedListener = new EventHandler<MouseEvent>() {

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
	EventHandler<ActionEvent> cellListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				if(selectedWarrior == null) {
					throw new UnselectedWarriorException("No Warrior has been selected!");
				}
				CellInterface cell = (CellInterface) event.getSource();
				cell.setOnMouseEntered(placementNotAllowedListener);
				int row = cell.getX();
				int col = cell.getY();
				Warrior warrior = WarriorFactory.getInstance().createWarrior(selectedWarrior.getID());
				Game.getInstance().getMap().takeCell(row, col, warrior);
				cell.setBackground(null);
				// TODO: if there are warriors of the same type available in the inventory, do not reset the cursor
				MainScene.getInstance().resetCursorImage();
				if(warriorSelected() && selectedWarrior.playsSound()) {
					SoundPlayer.getInstance().play(selectedWarrior.getID());
				}
				placementRows[row].getChildren().add(new PlacedWarrior(col, cellSize));
				selectedWarrior = null;
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while placing the warrior.");
			} catch(CellTakenException e) {
				System.out.println(e.getMessage());
			} catch(UnselectedWarriorException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};

}

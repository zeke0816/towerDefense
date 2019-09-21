package gui.layouts;

import characters.Warrior;
import exceptions.CellTakenException;
import exceptions.DatabaseException;
import exceptions.UnselectedWarriorException;
import game.Game;
import game.Map;
import gui.factories.WarriorFactory;
import gui.factories.warriors.WarriorInterface;
import gui.scenes.MainScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import javafx.util.Pair;
import media.BackgroundPlayer;
import media.databases.MediaDatabase;

public class MapInterface extends LayoutInterface<StackPane> {

	protected MediaPlayer mediaPlayer;
	protected MediaPlayer backgroundPlayer;
	protected GridPane placementLayout;
	protected StackPane[] placementRows;
	protected GridPane movementLayout;
	protected WarriorInterface selectedWarrior;
	protected final static int cellSize = 64;
	protected static final Duration fadeDuration = Duration.seconds(.5);
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
				Button cell = new Button();
				cell.setVisible(true);
				cell.setMinSize(cellSize, cellSize);
				cell.setMaxSize(cellSize, cellSize);
				cell.setPrefSize(cellSize, cellSize);
				cell.setBackground(null);
				cell.setOpacity(.6);
				cell.setUserData(new Pair<Integer, Integer>(i, j));
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
	 * Selects the Warrior to be placed in the map
	 * @param w the Warrior
	 */
	public void selectWarrior(WarriorInterface w) {
		selectedWarrior = w;
	}
	
	// TODO: create a sound player to allow multiple sounds to play at the same time and modularize this part
	// There is too much responsibility in this class
	
	/**
	 * Plays music, if necessary, given a path to the file
	 * @param path the path to the media file
	 */
	private void playSound(String id) {
		try {
			Media sound = MediaDatabase.getInstance().getSoundMedia(id);
			BackgroundPlayer.getInstance().fadeOut();
			if(mediaPlayer != null && mediaPlayer.getStatus().equals(Status.PLAYING)) {
				mediaPlayer.stop();
			}
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				
		        @Override
		        public void run() {
		        	BackgroundPlayer.getInstance().fadeIn();
		        }
		        
		    });
		} catch (DatabaseException e) {
			System.out.println("The Character's sound could not be played.");
		}
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
	 * Resets the cursor to its original default state
	 */
	private void resetCursorImage() {
		MainScene.getInstance().setCursor(Cursor.DEFAULT);
	}
	
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
				Button cell = (Button) event.getSource();
				cell.setOnMouseEntered(placementNotAllowedListener);
				Pair<Integer, Integer> coordinates = (Pair<Integer, Integer>) cell.getUserData();
				Integer row = coordinates.getKey();
				Integer col = coordinates.getValue();
				Warrior warrior = WarriorFactory.getInstance().createWarrior(selectedWarrior.getID());
				Game.getInstance().getMap().takeCell(row, col, warrior);
				cell.setBackground(null);
				// TODO: if there are warriors of the same type available in the inventory, do not reset the cursor
				resetCursorImage();
				if(selectedWarrior != null && selectedWarrior.playsSound()) {
					playSound(selectedWarrior.getID());
				}
				// TODO: let another class take the responsibility of creating the graphics
				Label placedWarrior = new Label();
				double paddingLeft = (col+1) * cellSize;
				placedWarrior.setPadding(new Insets(0, 0, 0, paddingLeft));
				// System.out.println("Col: "+col+". Left padding: "+paddingLeft+".");
				placedWarrior.setPrefHeight(cellSize);
				placedWarrior.setPrefWidth(cellSize);
				try {
					placedWarrior.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(selectedWarrior.getID(), cellSize, cellSize, true, false));
				} catch (DatabaseException e) {
					System.out.println("The Warrior's graphics could not be loaded and placed on the Map.");
				}
				placementRows[row].getChildren().add(placedWarrior);
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

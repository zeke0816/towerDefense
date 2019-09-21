package gui.layouts;

import java.io.File;

import characters.Warrior;
import exceptions.CellTakenException;
import exceptions.UnselectedWarriorException;
import game.Game;
import game.Map;
import gui.factories.WarriorFactory;
import gui.factories.warriors.WarriorInterface;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import javafx.util.Pair;

public class MapInterface extends LayoutInterface<StackPane>{

	protected MediaPlayer mediaPlayer;
	protected MediaPlayer backgroundPlayer;
	protected static final Duration fadeDuration = Duration.seconds(.5);
	protected Timeline fadeInTimeline;
	protected Timeline fadeOutTimeline;
	protected GridPane placementLayout;
	protected StackPane[] placementRows;
	protected GridPane movementLayout;
	protected WarriorInterface selectedWarrior;
	protected final static int cellSize = 64;
	private static final MapInterface instance = new MapInterface();
	private Scene appScene;

	protected MapInterface() {
		super();
		selectedWarrior = null;
		
		// TODO: Create special classes for music and stuff
		
		backgroundPlayer = new MediaPlayer(new Media(getMediaFromPath("src/assets/music/background.mp3")));
		backgroundPlayer.play();
		
		backgroundPlayer.setOnEndOfMedia(new Runnable() {
			
	        @Override
	        public void run() {
	        	backgroundPlayer.seek(Duration.ZERO);
	        	backgroundPlayer.play();
	        }
	        
	    });
		
		fadeInTimeline = new Timeline(new KeyFrame(fadeDuration, new KeyValue(backgroundPlayer.volumeProperty(), 1)));
		fadeOutTimeline = new Timeline(new KeyFrame(fadeDuration, new KeyValue(backgroundPlayer.volumeProperty(), .1)));
		
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
	
	/**
	 * Creates a background from an image, given its path, and returns it
	 * @param path the path to the background image
	 * @param width the width of the background
	 * @param height the height of the background
	 * @param cover whether the background will take the shape of the object
	 * @param contain whether the background will be adjusted to fit the shape of the object
	 * @return the background ready for use
	 */
	private Background createBackground(String path, double width, double height, boolean cover, boolean contain) {
		return new Background(new BackgroundImage(new Image(getMediaFromPath(path)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.RIGHT, 0, false, Side.TOP, 0, false), new BackgroundSize(width, height, false, false, contain, cover)));
	}
	
	//TODO: Use Proxy to get Media and stuff
	
	/**
	 * Gets media information as a String from a given path
	 * @param path the path to the media file
	 * @return the media information
	 */
	private String getMediaFromPath(String path) {
		return (new File(path)).toURI().toString();
	}
	
	/**
	 * Plays music, if necessary, given a path to the file
	 * @param path the path to the media file
	 */
	private void playMusic(String path) {
		fadeOutTimeline.play();
		if(mediaPlayer != null && mediaPlayer.getStatus().equals(Status.PLAYING)) {
			mediaPlayer.stop();
		}
		Media sound = new Media(getMediaFromPath(path));
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
	        @Override
	        public void run() {
	        	fadeInTimeline.play();
	        }
	        
	    });
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
		appScene.setCursor(Cursor.DEFAULT);
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
				if(selectedWarrior != null && selectedWarrior.playsMusic()) {
					playMusic("src/assets/music/"+selectedWarrior.getID()+".mp3");
				}
				Label placedWarrior = new Label();
				double paddingLeft = (col+1) * cellSize;
				placedWarrior.setPadding(new Insets(0, 0, 0, paddingLeft));
				// System.out.println("Col: "+col+". Left padding: "+paddingLeft+".");
				placedWarrior.setPrefHeight(cellSize);
				placedWarrior.setPrefWidth(cellSize);
				placedWarrior.setBackground(createBackground("src/assets/images/"+selectedWarrior.getID()+".png", cellSize, cellSize, false, true));
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

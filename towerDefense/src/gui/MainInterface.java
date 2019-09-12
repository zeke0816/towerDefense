package gui;

import java.io.File;

import characters.Warrior;
import exceptions.CellTakenException;
import exceptions.UnselectedWarriorException;
import game.Game;
import game.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Class to handle all things related to the General User Interface
 * @author zeke0816
 *
 */
public class MainInterface extends Application {
	
	protected Stage appStage;
	protected Scene appScene;
	protected BorderPane appLayout;
	protected Game game;
	protected Map map;
	protected FlowPane statusLayout;
	protected StackPane arenaLayout;
	protected GridPane placementLayout;
	protected StackPane[] placementRows;
	protected GridPane movementLayout;
	protected GridPane dockLayout;
	protected MediaPlayer mediaPlayer;
	protected MediaPlayer backgroundPlayer;
	protected WarriorInterface selectedWarrior;
	protected final static int cellSize = 64;
	protected static final Background darkBackground = new Background(new BackgroundFill(Paint.valueOf("#dddddd"), null, null));
	protected static final Background background = new Background(new BackgroundFill(Paint.valueOf("#ffffff"), null, null));
	protected static final Paint gray = Paint.valueOf("#8e8e8e");
	protected static final Paint black = Paint.valueOf("#000000");
	protected static final Duration fadeDuration = Duration.seconds(.5);
	protected Timeline fadeInTimeline;
	protected Timeline fadeOutTimeline;
	
	/**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
    	launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		initialize(stage);
	}
	
	/**
	 * Initializes the General User Interface with an empty game and arena
	 */
	private void initialize(Stage stage) {
		appStage = stage;
		appStage.setTitle("Cartoon Defense");
		appStage.centerOnScreen();
		appStage.setWidth(1500);
		appStage.setHeight(850);
		
		selectedWarrior = null;
		
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
		
		appLayout = new BorderPane();
        appLayout.setBackground(new Background(new BackgroundImage(new Image(getMediaFromPath("src/assets/images/background.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1920, 1080, false, false, false, false))));
        
        appScene = new Scene(appLayout);
        appScene.getStylesheets().add(getMediaFromPath("src/assets/min.style.css"));
		
        statusLayout = new FlowPane();
        Label gameTitle = new Label("Cartoon Defense");
        gameTitle.setAlignment(Pos.CENTER);
        gameTitle.setFont(new Font("Cambria", 50));
        statusLayout.setAlignment(Pos.CENTER);
        statusLayout.getChildren().add(gameTitle);
        statusLayout.setPrefHeight(120);
        
        arenaLayout = new StackPane();
        movementLayout = new GridPane();
        placementLayout = new GridPane();
        
        arenaLayout.setAlignment(Pos.CENTER);
        movementLayout.setAlignment(Pos.CENTER);
        placementLayout.setAlignment(Pos.CENTER);

        game = new Game();
		map = game.getMap();
		
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
		
		arenaLayout.getChildren().addAll(movementLayout, placementLayout);
		arenaLayout.setAlignment(Pos.CENTER);
        
		dockLayout = new GridPane();
        dockLayout.setAlignment(Pos.CENTER);
        dockLayout.setPrefHeight(120);
        
        WarriorInterface agentP = new WarriorInterface("AgentP", "Agent P", true);
        agentP.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(agentP.getButton(), 0, 0);
        dockLayout.add(agentP.getLabel(), 0, 1);
        GridPane.setHalignment(agentP.getLabel(), HPos.CENTER);
        
        WarriorInterface flea = new WarriorInterface("TheFlea", "The Flea", true);
        flea.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(flea.getButton(), 1, 0);
        dockLayout.add(flea.getLabel(), 1, 1);
        GridPane.setHalignment(flea.getLabel(), HPos.CENTER);

        WarriorInterface cyborg = new WarriorInterface("Cyborg", "Cyborg", true);
        cyborg.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(cyborg.getButton(), 2, 0);
        dockLayout.add(cyborg.getLabel(), 2, 1);
        GridPane.setHalignment(cyborg.getLabel(), HPos.CENTER);
        
        WarriorInterface bb8 = new WarriorInterface("BB8", "BB8", false);
        bb8.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(bb8.getButton(), 3, 0);
        dockLayout.add(bb8.getLabel(), 3, 1);
        GridPane.setHalignment(bb8.getLabel(), HPos.CENTER);
        
        WarriorInterface gary = new WarriorInterface("Gary", "Gary", true);
        gary.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(gary.getButton(), 4, 0);
        dockLayout.add(gary.getLabel(), 4, 1);
        GridPane.setHalignment(gary.getLabel(), HPos.CENTER);
        
        WarriorInterface turret = new WarriorInterface("Turret", "Turret", false);
        turret.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(turret.getButton(), 5, 0);
        dockLayout.add(turret.getLabel(), 5, 1);
        GridPane.setHalignment(turret.getLabel(), HPos.CENTER);
        
        WarriorInterface toph = new WarriorInterface("Toph", "Toph", false);
        toph.getButton().setOnAction(selectWarriorListener);
        dockLayout.add(toph.getButton(), 6, 0);
        dockLayout.add(toph.getLabel(), 6, 1);
        GridPane.setHalignment(toph.getLabel(), HPos.CENTER);
		
		appLayout.setTop(statusLayout);
		appLayout.setCenter(arenaLayout);
		appLayout.setBottom(dockLayout);
		
		appStage.setScene(appScene);
		appStage.show();
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
	
	/**
	 * Gets media information as a String from a given path
	 * @param path the path to the media file
	 * @return the media information
	 */
	private String getMediaFromPath(String path) {
		return (new File(path)).toURI().toString();
	}
	
	/**
	 * Resets the cursor to its original default state
	 */
	private void resetCursorImage() {
		appScene.setCursor(Cursor.DEFAULT);
	}
	
	/**
	 * Sets a custom image as the cursor
	 * @param img the image
	 */
	private void setCursorImage(Image img) {
		appScene.setCursor(new ImageCursor(img, img.getWidth()/2, img.getHeight()/2));
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
	 * Listener for warrior selection from the dock
	 */
	EventHandler<ActionEvent> selectWarriorListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				Button warrior = (Button) event.getSource();
				selectedWarrior = (WarriorInterface) warrior.getUserData();
				Image img = new Image(getMediaFromPath("src/assets/cursors/"+selectedWarrior.getID()+".png"));
				setCursorImage(img);
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			}
		}
		
	};
	
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
				Button cell = (Button) event.getSource();
				cell.setOnMouseEntered(placementNotAllowedListener);
				Pair<Integer, Integer> coordinates = (Pair<Integer, Integer>) cell.getUserData();
				Integer row = coordinates.getKey();
				Integer col = coordinates.getValue();
				Warrior warrior = game.getFactory().createWarrior(selectedWarrior.getID());
				map.takeCell(row, col, warrior);
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

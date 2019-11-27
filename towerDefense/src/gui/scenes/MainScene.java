package gui.scenes;

import exceptions.DatabaseException;
import game.Game;
import gui.layouts.BaseLayout;
import gui.layouts.DockLayout;
import gui.layouts.MapLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import media.MediaDatabase;
import media.sounds.BackgroundPlayer;
import visitors.GameVisitor;
import visitors.PauseResumeGame;
import visitors.ShowCredits;
import visitors.ShowWelcome;
import visitors.StartNewGame;


/**
 * The Main Scene is the class that manages the main window of the game.
 * Essentially, most of the interaction between the system and the user happens in here.
 * 
 * @author zeke0816
 *
 */
public class MainScene extends Scene {
	
	private static final StackPane layout = new StackPane();
	private static final BorderPane gameLayout = new BorderPane();
	private static final BorderPane statusLayout = new BorderPane();
	private static final MainScene instance = new MainScene(layout);
	private Label statusLabel;
	private EventHandler<KeyEvent> pauseListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			GameVisitor action;
			if(key.getCode() == KeyCode.N) {
				action = new StartNewGame();
				Game.getInstance().state().accept(action);
			}
			if(key.getCode() == KeyCode.P) {
				action = new PauseResumeGame();
				Game.getInstance().state().accept(action);
			}
			if(key.getCode() == KeyCode.C) {
				action = new ShowCredits();
				Game.getInstance().state().accept(action);
			}
			if(key.getCode() == KeyCode.W) {
				action = new ShowWelcome();
				Game.getInstance().state().accept(action);
			}
			if(key.getCode() == KeyCode.I) {
				DockLayout.getInstance().toggleInventory();
			}
			if(key.getCode() == KeyCode.ESCAPE) {
				PlacementLayout.getInstance().deselectObject();
				MapLayout.getInstance().allowPicking();
			}
		}
		
	};

	/**
	 * Initializes the Main Scene by setting the default style values, the background music, the background image, and the main layouts.
	 * @param parent the main layout
	 */
	public MainScene(Parent parent) {
		super(parent);
		BackgroundPlayer.getInstance().play();
		try {
			layout.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("background", 1136, 720, false, true));
		} catch (DatabaseException e) {
			System.out.println("The main Scene's background image could not be loaded.");
		}
		statusLabel = new Label();
		statusLabel.setOpacity(.6);
		statusLabel.setFont(new Font("Trebuchet", 20));
		statusLabel.setTextFill(Paint.valueOf("#fff"));
		statusLabel.setTextAlignment(TextAlignment.CENTER);
		statusLabel.setPadding(new Insets(10, 16, 10, 16));
		statusLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000"), null, null)));
        gameLayout.setTop(StatusLayout.getInstance().getLayout());
        gameLayout.setLeft(BaseLayout.getInstance().getLayout());
        gameLayout.setCenter(MapLayout.getInstance().getLayout());
        gameLayout.setBottom(DockLayout.getInstance().getLayout());
        setOnKeyPressed(pauseListener);
        welcome();
	}
	
	/**
	 * Shows the welcome screen
	 */
	public void welcome() {
		prepareStatus();
		statusLabel.setText("Welcome to CARTOON DEFENSE!\n\n"+
		"Press the 'N' key whenever you are ready to start the game\n\n"+
		"Please, keep the following keyboard shortcuts in mind:\n\n"+
		"P - Pause the game\n"+
		"I - Toggle inventory / store\n"+
		"ESC - Deselect an object\n\n"+
		"There are 3 indicators on the top:\n\n"+
		"LEFT - Budget\n"+
		"CENTER - Points\n"+
		"RIGHT - Level / Wave / Killed enemies : enemies in the current Wave\n\n"+
		"Have fun and thank you for playing our game!\n\n"+
		"Press the 'C' key to see the credits screen.");
	}
	
	/**
	 * Shows the credits screen
	 */
	public void credits() {
		prepareStatus();
		statusLabel.setText("CREDITS\n\n"+
		"DEVELOPERS\n\n"+
		"Дмитро Школяр\n"+
		"黄西结\n"+
		"Vanesa Y. Mamani\n\n"+
		"SPECIAL THANKS TO\n\n"+
		"Iñaki Barreix - Project Supervisor\n"+
		"José Ignacio Ochoa (Pepe) - Project Leader\n"+
		"Federico Virkel - Project Leader\n"+
		"Mariano Lavopa - Project Leader\n\n"+
		"© 2019. All rights reserved.\n\n"+
		"Press the 'W' key to see the welcome screen and the 'N' key to start a new game.");
	}
	
	/**
	 * Shows the victory screen
	 */
	public void victory() {
		prepareStatus();
		statusLabel.setText("VICTORY!\n\n"+
		"You have won the game. Good job! Press the 'N' key to start again\n"+
		"Press the 'W' key to see the welcome screen\n"+
		"Press the 'C' key to see the credits.");
	}
	
	/**
	 * Shows the loss screen
	 */
	public void loss() {
		prepareStatus();
		statusLabel.setText("DEFEAT!\n\n"+
		"You have lost the game. Sad face :(. Press the 'N' key to start again"+
		"Press the 'W' key to see the welcome screen\n"+
		"Press the 'C' key to see the credits.");
	}
	
	/**
	 * Shows the pause screen
	 */
	public void pause() {
		prepareStatus();
        statusLabel.setText("PAUSED.\n\n"+
		"You may press the 'N' key to restart the game if you wish or the 'P' key to resume");
	}
	
	private void prepareStatus() {
		statusLayout.setCenter(statusLabel);
		layout.getChildren().clear();
        layout.getChildren().addAll(gameLayout, statusLayout);
	}
	
	/**
	 * Removes the pause screen
	 */
	public void resume() {
		statusLayout.setCenter(null);
		layout.getChildren().clear();
        layout.getChildren().addAll(statusLayout, gameLayout);
	}

	/**
	 * Gets the instance of this class
	 * @return the instance
	 */
	public static MainScene getInstance() {
		return instance;
	}
	
	/**
	 * Gets the layout of the Main Scene
	 * @return the layout
	 */
	public StackPane getLayout() {
		return layout;
	}
	
	/**
	 * Resets the cursor to its original default state
	 */
	public void resetCursorImage() {
		setCursor(Cursor.DEFAULT);
	}
	
	/**
	 * Sets a custom image as the cursor
	 * @param img the image
	 */
	public void setCursorImage(Image img) {
		setCursor(new ImageCursor(img, img.getWidth()/2, img.getHeight()/2));
	}

}

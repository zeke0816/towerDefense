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
import media.MediaDatabase;
import media.sounds.BackgroundPlayer;

public class MainScene extends Scene {
	
	private static final StackPane layout = new StackPane();
	private static final BorderPane gameLayout = new BorderPane();
	private static final BorderPane pauseLayout = new BorderPane();
	private static final MainScene instance = new MainScene(layout);
	private Label pauseLabel;
	private EventHandler<KeyEvent> pauseListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			if(key.getCode() == KeyCode.P) {
				Game game = Game.getInstance();
				if(game.paused()) {
					game.resume();
					resume();
				} else {
					game.pause();
					pause();
				}
			}
			if(key.getCode() == KeyCode.I) {
				DockLayout.getInstance().toggleInventory();
			}
			if(key.getCode() == KeyCode.ESCAPE) {
				PlacementLayout.getInstance().deselectWarrior();
				PlacementLayout.getInstance().deselectItem();
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
		pauseLabel = new Label();
		pauseLabel.setOpacity(.6);
		pauseLabel.setText("PAUSED");
		pauseLabel.setFont(new Font("Trebuchet", 20));
		pauseLabel.setTextFill(Paint.valueOf("#fff"));
		pauseLabel.setPadding(new Insets(10, 16, 10, 16));
		pauseLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000"), null, null)));
        gameLayout.setTop(StatusLayout.getInstance().getLayout());
        gameLayout.setLeft(BaseLayout.getInstance().getLayout());
        gameLayout.setCenter(MapLayout.getInstance().getLayout());
        gameLayout.setBottom(DockLayout.getInstance().getLayout());
        pauseLayout.setCenter(pauseLabel);
        setOnKeyPressed(pauseListener);
        pause();
	}
	
	/**
	 * Shows the pause screen
	 */
	private void pause() {
		pauseLayout.setCenter(pauseLabel);
		layout.getChildren().clear();
        layout.getChildren().addAll(gameLayout, pauseLayout);
	}
	
	/**
	 * Removes the pause screen
	 */
	private void resume() {
		pauseLayout.setCenter(null);
		layout.getChildren().clear();
        layout.getChildren().addAll(pauseLayout, gameLayout);
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

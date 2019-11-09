package gui.scenes;

import exceptions.DatabaseException;
import game.Game;
import gui.layouts.BaseLayout;
import gui.layouts.DockLayout;
import gui.layouts.DroppingLayout;
import gui.layouts.InventoryLayout;
import gui.layouts.MapLayout;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;
import gui.layouts.StoreLayout;
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
	private static final BorderPane statusLayout = new BorderPane();
	private static final MainScene instance = new MainScene(layout);
	private Label statusLabel;
	private EventHandler<KeyEvent> pauseListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			Game game = Game.getInstance();
			if(key.getCode() == KeyCode.N) {
				if(!game.hasStarted() && (game.isOver() || game.isBeaten())) {
					game.startNew();
					MovementLayout.getInstance().flush();
					DroppingLayout.getInstance().flush();
					StoreLayout.getInstance().updateAvailability();
					InventoryLayout.getInstance().updateAvailability();
					game.start();
					resume();
				}
			}
			if(key.getCode() == KeyCode.P) {
				if(game.hasStarted()) {
					if(game.paused()) {
						game.resume();
						resume();
					} else {
						game.pause();
						pause();
					}
				}
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
		statusLabel.setText("PRESS N TO START A NEW GAME");
		statusLabel.setFont(new Font("Trebuchet", 20));
		statusLabel.setTextFill(Paint.valueOf("#fff"));
		statusLabel.setPadding(new Insets(10, 16, 10, 16));
		statusLabel.setBackground(new Background(new BackgroundFill(Paint.valueOf("#000"), null, null)));
        gameLayout.setTop(StatusLayout.getInstance().getLayout());
        gameLayout.setLeft(BaseLayout.getInstance().getLayout());
        gameLayout.setCenter(MapLayout.getInstance().getLayout());
        gameLayout.setBottom(DockLayout.getInstance().getLayout());
        statusLayout.setCenter(statusLabel);
        setOnKeyPressed(pauseListener);
        layout.getChildren().addAll(gameLayout, statusLayout);
	}
	
	/**
	 * Shows the victory screen
	 */
	public void victory() {
		pause();
		statusLabel.setText("VICTORY! YOU HAVE WON THE GAME. PRESS N TO START AGAIN");
	}
	
	/**
	 * Shows the game over screen
	 */
	public void gameOver() {
		pause();
		statusLabel.setText("GAME OVER! PRESS N TO START A NEW GAME");
	}
	
	/**
	 * Shows the pause screen
	 */
	private void pause() {
		statusLayout.setCenter(statusLabel);
		statusLabel.setText("PAUSED");
		layout.getChildren().clear();
        layout.getChildren().addAll(gameLayout, statusLayout);
	}
	
	/**
	 * Removes the pause screen
	 */
	private void resume() {
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

package gui.scenes;

import exceptions.DatabaseException;
import gui.layouts.DockInterface;
import gui.layouts.MapInterface;
import gui.layouts.StatusInterface;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import media.databases.MediaDatabase;
import media.sounds.BackgroundPlayer;

public class MainScene extends Scene {
	
	private static final BorderPane layout = new BorderPane();
	private static final MainScene instance = new MainScene(layout);

	/**
	 * Initializes the Main Scene by setting the default style values, the background music, the background image, and the main layouts.
	 * @param parent the main layout
	 */
	public MainScene(Parent parent) {
		super(parent);
		/*
		try {
			getStylesheets().add(MediaDatabase.getInstance().getStyleMedia("mainScene"));
		} catch (DatabaseException e) {
			System.out.println("The main Scene's default style sheet could not be loaded.");
		}
		*/
		BackgroundPlayer.getInstance().play();
		try {
			layout.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("background", 1920, 1080, false, false));
		} catch (DatabaseException e) {
			System.out.println("The main Scene's background image could not be loaded.");
		}
        layout.setTop(StatusInterface.getInstance().getLayout());
        layout.setCenter(MapInterface.getInstance().getLayout());
        layout.setBottom(DockInterface.getInstance().getLayout());
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
	public BorderPane getLayout() {
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

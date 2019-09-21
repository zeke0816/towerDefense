package gui.scenes;

import java.io.File;

import gui.layouts.DockInterface;
import gui.layouts.MapInterface;
import gui.layouts.StatusInterface;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class MainScene extends Scene {
	
	private static final BorderPane layout = new BorderPane();
	private static final MainScene instance = new MainScene(layout);

	public MainScene(Parent parent) {
		super(parent);
		
		getStylesheets().add(getMediaFromPath("src/media/styles/min.css"));
		
		layout.setBackground(new Background(new BackgroundImage(new Image(getMediaFromPath("src/media/images/background.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1920, 1080, false, false, false, false))));
        layout.setTop(StatusInterface.getInstance().getLayout());
        layout.setCenter(MapInterface.getInstance().getLayout());
        layout.setBottom(DockInterface.getInstance().getLayout());
	}
	
	public static MainScene getInstance() {
		return instance;
	}
	
	public BorderPane getLayout() {
		return layout;
	}
	
	// TODO: Use a special class to load media and stuff
	
	/**
	 * Gets media information as a String from a given path
	 * @param path the path to the media file
	 * @return the media information
	 */
	private String getMediaFromPath(String path) {
		return (new File(path)).toURI().toString();
	}

}

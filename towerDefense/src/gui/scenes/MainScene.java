package gui.scenes;

import exceptions.DatabaseException;
import gui.layouts.DockInterface;
import gui.layouts.MapInterface;
import gui.layouts.StatusInterface;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import media.databases.MediaDatabase;

public class MainScene extends Scene {
	
	private static final BorderPane layout = new BorderPane();
	private static final MainScene instance = new MainScene(layout);

	public MainScene(Parent parent) {
		super(parent);
		/*
		try {
			getStylesheets().add(MediaDatabase.getInstance().getStyleMedia("mainScene"));
		} catch (DatabaseException e) {
			System.out.println("The main Scene's default style sheet could not be loaded.");
		}
		*/
		try {
			layout.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("background", 1920, 1080, false, false));
		} catch (DatabaseException e) {
			System.out.println("The main Scene's background image could not be loaded.");
		}
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

}

package gui;

import java.io.File;

import game.Game;
import game.Map;
import gui.layouts.DockInterface;
import gui.layouts.MapInterface;
import gui.layouts.StatusInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

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
	protected GridPane dockLayout;
	protected static final Background darkBackground = new Background(new BackgroundFill(Paint.valueOf("#dddddd"), null, null));
	protected static final Background background = new Background(new BackgroundFill(Paint.valueOf("#ffffff"), null, null));
	protected static final Paint gray = Paint.valueOf("#8e8e8e");
	protected static final Paint black = Paint.valueOf("#000000");
	private StackPane arenaLayout;
	
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
		
		appLayout = new BorderPane();
        appLayout.setBackground(new Background(new BackgroundImage(new Image(getMediaFromPath("src/assets/images/background.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1920, 1080, false, false, false, false))));
        
        appScene = new Scene(appLayout);
        appScene.getStylesheets().add(getMediaFromPath("src/assets/min.style.css"));
        
        StatusInterface.getInstance().setScene(appScene);
        statusLayout = StatusInterface.getInstance().getLayout();
        
        MapInterface.getInstance().setScene(appScene);
        arenaLayout = MapInterface.getInstance().getLayout();
        
        DockInterface.getInstance().setScene(appScene);
        dockLayout = DockInterface.getInstance().getLayout();
		
		appLayout.setTop(statusLayout);
		appLayout.setCenter(arenaLayout);
		appLayout.setBottom(dockLayout);
		
		appStage.setScene(appScene);
		appStage.show();
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

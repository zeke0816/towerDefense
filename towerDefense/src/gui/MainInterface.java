package gui;

import gui.scenes.MainScene;
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Class to handle all things related to the General User Interface
 * @author zeke0816
 *
 */
public class MainInterface extends Application {
	
	protected static final Background darkBackground = new Background(new BackgroundFill(Paint.valueOf("#dddddd"), null, null));
	protected static final Background background = new Background(new BackgroundFill(Paint.valueOf("#ffffff"), null, null));
	protected static final Paint gray = Paint.valueOf("#8e8e8e");
	protected static final Paint black = Paint.valueOf("#000000");
	
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
		stage.setTitle("Cartoon Defense");
		stage.centerOnScreen();
		stage.setWidth(1500);
		stage.setHeight(850);
		stage.setScene(MainScene.getInstance());
		stage.show();
	}
	
}

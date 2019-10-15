package gui;

import game.Game;
import gui.scenes.MainScene;
import javafx.application.Application;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Class to handle all things related to the General User Interface
 * @author zeke0816
 *
 */
public class MainInterface extends Application {
	
	// protected static final Background darkBackground = new Background(new BackgroundFill(Paint.valueOf("#dddddd"), null, null));
	// protected static final Background background = new Background(new BackgroundFill(Paint.valueOf("#ffffff"), null, null));
	// protected static final Paint gray = Paint.valueOf("#8e8e8e");
	// protected static final Paint black = Paint.valueOf("#000000");
	protected Thread movementThread;
	protected Thread battleThread;
	
	/**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
    	launch(args);
    }
    
    /**
     * Starts the application
     */
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("Dmytro, your PC is the problem.");
		
		stage.setTitle("Cartoon Defense");
		stage.centerOnScreen();
		stage.setWidth(1500);
		stage.setHeight(850);
		stage.setScene(MainScene.getInstance());
		stage.show();
		
        movementThread = new Thread(new Movement(), "Enemy Movement Thread");
        movementThread.start();
		
        battleThread = new Thread(new Battle(), "Enemy Movement Thread");
        battleThread.start();
	}
	
	/**
	 * Handles unexpected exits (user clicks on the close button)
	 */
	@Override
	public void stop() {
		Game.getInstance().end();
	}
	
}

package gui;

import game.Game;
import game.states.Welcome;
import gui.scenes.MainScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import visitors.GameActionVisitor;

/**
 * Class to handle all things related to the General User Interface
 * @author zeke0816
 *
 */
public class CartoonDefense extends Application {
	
	protected GameActionVisitor action;
	
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
	public void start(Stage stage) {
		stage.setTitle("Cartoon Defense");
		stage.centerOnScreen();
		stage.setWidth(1200);
		stage.setHeight(900);
		stage.setScene(MainScene.getInstance());
		stage.show();
		
		action = new GameActionVisitor();
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Game game = Game.getInstance();
				game.state().accept(action);
			}
        	
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
	
	/**
	 * Handles unexpected exits (user clicks on the close button)
	 */
	@Override
	public void stop() {
		Game.getInstance().changeState(new Welcome(Game.getInstance()));
	}
	
}

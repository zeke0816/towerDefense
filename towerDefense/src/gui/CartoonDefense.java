package gui;

import java.util.ArrayList;

import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.items.charm.temporary.TemporaryCharm;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;
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
import visitors.BattleVisitor;

/**
 * Class to handle all things related to the General User Interface
 * @author zeke0816
 *
 */
public class CartoonDefense extends Application {
	
	protected BattleVisitor battle;
	
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
		
		Map map = Game.getInstance().getMap();
		battle = new BattleVisitor();
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Game game = Game.getInstance();
				if(game.hasStarted() && (!game.isOver() && !game.paused())) {
					ArrayList<TemporaryCharm> wornOffCharms = game.checkTemporaryCharms();
					for(TemporaryCharm charm: wornOffCharms) {
						GameObject object = charm.getObject();
						if(object.isAlive()) {
							MovementLayout.getInstance().wearCharmOff(object);
						}
					}
					PlacementLayout.getInstance().spawnItem();
					PlacementLayout.getInstance().spawnEnemy();
					ArrayList<GameObject> objects = map.getGameObjectList();
					for(GameObject object: objects) {
						if(object.isAlive()) {
							object.accept(battle);
						}
						StatusLayout.getInstance().updateBudget();
					}
				}
				if(game.hasStarted()) {
					if(game.isOver()) {
						MainScene.getInstance().gameOver();
					}
					if(game.isBeaten()) {
						MainScene.getInstance().victory();
					}
				}
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
		Game.getInstance().end();
	}
	
}

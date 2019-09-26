package gui.layouts;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class StatusLayout extends Layout<FlowPane> {

	private Label score;
	private static final StatusLayout instance = new StatusLayout();
	
	protected StatusLayout() {
		super();
        
        score = new Label("Score: "+Game.getInstance().getScore());
        score.setAlignment(Pos.TOP_RIGHT);
        score.setFont(new Font("Cambria", 30));
        
		layout = new FlowPane();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(score);
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static StatusLayout getInstance() {
		return instance;
	}
	
	public void updateScore() {
		score.setText("Score: "+Game.getInstance().getScore());
	}
	
}

package gui.layouts;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class StatusLayout extends Layout<FlowPane> {

	private static final StatusLayout instance = new StatusLayout();
	
	protected StatusLayout() {
		super();
		layout = new FlowPane();
        Label gameTitle = new Label("Cartoon Defense");
        gameTitle.setAlignment(Pos.CENTER);
        gameTitle.setFont(new Font("Cambria", 50));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(gameTitle);
        layout.setPrefHeight(120);
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static StatusLayout getInstance() {
		return instance;
	}
	
}

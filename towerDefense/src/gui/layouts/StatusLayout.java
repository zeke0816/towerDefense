package gui.layouts;

import exceptions.DatabaseException;
import game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import media.databases.MediaDatabase;

public class StatusLayout extends Layout<FlowPane> {

	private Label score;
	private Label budget;
	private Label level;
	private static final StatusLayout instance = new StatusLayout();
	
	protected StatusLayout() {
		super();
        
		budget = new Label();
		updateBudget();
		budget.setAlignment(Pos.CENTER_RIGHT);
		budget.setFont(new Font("Trebuchet", 20));
		budget.setTextFill(Paint.valueOf("#fff"));
		budget.setPrefWidth(250);
		budget.setPrefHeight(50);
		try {
			Background img = MediaDatabase.getInstance().getImageBackgroundMedia("budget", 1000, 200, true, false);
			budget.setBackground(img);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
        
        score = new Label();
        updateScore();
        score.setAlignment(Pos.CENTER_RIGHT);
        score.setFont(new Font("Trebuchet", 20));
        score.setTextFill(Paint.valueOf("#fff"));
        score.setPrefWidth(250);
        score.setPrefHeight(50);
		try {
			Background img = MediaDatabase.getInstance().getImageBackgroundMedia("points", 1000, 200, true, false);
			score.setBackground(img);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
        
        level = new Label();
        updateLevel();
        level.setAlignment(Pos.CENTER_RIGHT);
        level.setFont(new Font("Trebuchet", 20));
        level.setTextFill(Paint.valueOf("#fff"));
        level.setPrefWidth(250);
        level.setPrefHeight(50);
		try {
			Background img = MediaDatabase.getInstance().getImageBackgroundMedia("level", 1000, 200, true, false);
			level.setBackground(img);
		} catch (DatabaseException e) {
			System.out.println(e.getMessage());
		}
        
		layout = new FlowPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(120);
        layout.getChildren().addAll(budget, score, level);
        FlowPane.setMargin(budget, new Insets(10, 10, 10, 10));
        FlowPane.setMargin(score, new Insets(10, 10, 10, 10));
        FlowPane.setMargin(level, new Insets(10, 10, 10, 10));
	}

	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static StatusLayout getInstance() {
		return instance;
	}
	
	public void updateScore() {
		score.setText(""+Game.getInstance().getScore()+"  ");
	}
	
	public void updateBudget() {
		budget.setText("$ "+Game.getInstance().getBudget()+"  ");
	}
	
	public void updateLevel() {
		level.setText(""+Game.getInstance().getLevel().getValue()+"  ");
	}
	
}

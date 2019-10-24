package gui.attacks;

import exceptions.DatabaseException;
import game.objects.GameObject;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import media.databases.MediaDatabase;

public class ExplosionBlast extends Attack {
	
	protected double size;
	protected int yPos;
	protected int xPos;
	protected ExplosionBlast instance;

	/**
	 * Creates an Explosion Blast at an XY position distant to the base
	 * @param row the row coordinate
	 * @param col the column coordinate
	 */
	public ExplosionBlast(int row, int col) {
		super();
		
		yPos = row;
		xPos = col;
		size = PlacementLayout.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size);
		setVisible(true);
		setTranslateX(xPos * size);
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("explosionBlast", size, size, true, false));
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
		MovementLayout.getInstance().addBlast(yPos, this);
		instance = this;
	}
	
	/**
	 * Listener for the explosion
	 */
	private EventHandler<ActionEvent> explosionAction = new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0) {
			
		}
		
	};
	
	public void shoot(GameObject object) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), explosionAction));
		timeline.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				MovementLayout.getInstance().removeBlast(yPos, instance);
			}
			
		});
		timeline.setCycleCount(1);
		timeline.play();
	}

}

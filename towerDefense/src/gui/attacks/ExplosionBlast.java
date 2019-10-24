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
	protected int xPos;
	protected int yPos;
	protected ExplosionBlast instance;

	/**
	 * Creates an Explosion Blast at an XY position distant to the base
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 */
	public ExplosionBlast(int x, int y) {
		super();
		
		xPos = x;
		yPos = y;
		size = PlacementLayout.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size);
		setVisible(true);
		instance = this;
		MovementLayout.getInstance().addBlast(yPos, instance);
	}
	
	public void shoot(GameObject object) {
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("explosionBlast", size, size, true, false));
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					setTranslateX(xPos);
				}
	        	
	        }));
			timeline.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					MovementLayout.getInstance().removeBlast(yPos, instance);
				}
	        	
	        });
			timeline.setCycleCount(2);
			timeline.play();
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
	}

}

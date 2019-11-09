package gui.attacks;

import exceptions.DatabaseException;
import game.Map;
import game.objects.GameObject;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import media.MediaDatabase;

public class EnergyBlast extends Attack {
	
	protected int destination;
	protected int current;
	protected int duration;
	protected EnergyBlast instance;

	/**
	 * Creates an Energy Blast traveling from a source distance from/to the base to a destination distance from/to the base
	 * @param lane the lane where the blast is thrown
	 * @param s the source position
	 * @param d the destination position
	 */
	public EnergyBlast(int lane, int s, int d) {
		super();
		
		setTranslateY(lane * Map.cellSize);
		current = s;
		destination = d;
		duration = Math.abs(destination - current);
		
		updatePosition(current);
		setPrefHeight(Map.cellSize);
		setPrefWidth(Map.cellSize);
		setVisible(true);
		instance = this;
		MovementLayout.getInstance().addBlast(instance);
	}
	
	public void shoot(GameObject object) {
		try {
			if(destination > current) {
				// shoot right
				setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("energyBlastRight", Map.cellSize, Map.cellSize, true, false));
				Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
	
					@Override
					public void handle(ActionEvent arg0) {
						current++;
						updatePosition(current);
					}
		        	
		        }));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						MovementLayout.getInstance().removeBlast(instance);
						checkDeath(object);
					}
		        	
		        });
				timeline.setCycleCount(duration);
				timeline.play();
			} else {
				// shoot left
				setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("energyBlastLeft", Map.cellSize, Map.cellSize, true, false));
				Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						current--;
						updatePosition(current);
					}
		        	
		        }));
				timeline.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						MovementLayout.getInstance().removeBlast(instance);
						checkDeath(object);
					}
		        	
		        });
				timeline.setCycleCount(duration);
				timeline.play();
			}
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Updated the position of the blast
	 * @param p the new position
	 */
	private void updatePosition(double p) {
		setTranslateX(p);
	}
	
	private void checkDeath(GameObject object) {
		if(object.isDead()) {
			PlacementLayout.getInstance().killObject(object);
			StatusLayout.getInstance().updateLevel();
		}
	}
	
}

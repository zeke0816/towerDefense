package gui.attacks;

import exceptions.DatabaseException;
import game.objects.GameObject;
import gui.layouts.MovementLayout;
import gui.layouts.PlacedObject;
import gui.layouts.PlacementLayout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import media.databases.MediaDatabase;

public class EnergyBlast extends Attack {
	
	protected double destination;
	protected double current;
	protected int duration;
	protected double size;
	protected int row;
	protected EnergyBlast instance;

	/**
	 * Creates an Energy Blast traveling from a source distance from/to the base to a destination distance from/to the base
	 * @param a the attacking Object
	 * @param b the blown up Object
	 */
	public EnergyBlast(PlacedObject attacker, PlacedObject blownup) {
		super();
		
		row = attacker.getRow();
		size = PlacementLayout.getCellSize();
		current = attacker.getCurrentPosition();
		destination = blownup.getCurrentPosition();
		duration = Integer.parseInt(Double.toString(Math.abs(destination - current)).split("\\.")[0]);
		
		updatePosition(current);
		setPrefHeight(size);
		setPrefWidth(size);
		setVisible(true);
		instance = this;
		MovementLayout.getInstance().addBlast(row, instance);
	}
	
	public void shoot(GameObject object) {
		try {
			if(destination > current) {
				// shoot right
				setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("energyBlastRight", size, size, true, false));
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
						MovementLayout.getInstance().removeBlast(row, instance);
						checkDeath(object);
					}
		        	
		        });
				timeline.setCycleCount(duration);
				timeline.play();
			} else {
				// shoot left
				setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("energyBlastLeft", size, size, true, false));
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
						MovementLayout.getInstance().removeBlast(row, instance);
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
		}
	}
	
}

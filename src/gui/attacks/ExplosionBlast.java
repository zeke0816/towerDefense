package gui.attacks;

import exceptions.DatabaseException;
import game.objects.GameObject;
import gui.layouts.MovementLayout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BackgroundPosition;
import javafx.util.Duration;
import media.MediaDatabase;

public class ExplosionBlast extends Attack {
	
	protected ExplosionBlast instance;

	/**
	 * Creates an Explosion Blast at a given distance to the base
	 * @param d the distance to the base
	 */
	public ExplosionBlast(int left, int top, int scope) {
		super();
		
		setPrefHeight(scope);
		setPrefWidth(scope);
		setVisible(true);
		setTranslateY(top);
		setTranslateX(left);
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("explosionBlast", scope, scope, true, false, BackgroundPosition.CENTER));
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
		MovementLayout.getInstance().addBlast(this);
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
				MovementLayout.getInstance().removeBlast(instance);
			}
			
		});
		timeline.setCycleCount(1);
		timeline.play();
	}

}

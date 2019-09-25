package gui.factories.enemies;

import exceptions.DatabaseException;
import game.Game;
import gui.layouts.PlacementLayout;
import javafx.scene.control.Label;
import media.databases.MediaDatabase;

public class PlacedEnemy extends Label {

	public PlacedEnemy(String id) {
		super();
		
		double size = PlacementLayout.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size);
		setTranslateX((Game.getInstance().getMap().getColumns() - 1) * size);
		
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded and placed on the Map.");
		}
	}

}

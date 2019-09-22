package gui.factories.warriors;

import exceptions.DatabaseException;
import gui.layouts.PlacementInterface;
import javafx.scene.control.Label;
import media.databases.MediaDatabase;

public class PlacedWarrior extends Label {

	public PlacedWarrior(int col) {
		super();
		
		double size = PlacementInterface.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size);
		setTranslateX(col * size);
		
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(PlacementInterface.getInstance().getSelectedWarrior().getID(), size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded and placed on the Map.");
		}
	}
	
}

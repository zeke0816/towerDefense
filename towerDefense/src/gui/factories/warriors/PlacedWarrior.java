package gui.factories.warriors;

import exceptions.DatabaseException;
import gui.layouts.PlacementInterface;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import media.databases.MediaDatabase;

public class PlacedWarrior extends Label {

	public PlacedWarrior(int col) {
		super();
		
		double size = PlacementInterface.getCellSize();
		double paddingLeft = (col+1) * size;
		
		setPadding(new Insets(0, 0, 0, paddingLeft));
		setPrefHeight(size);
		setPrefWidth(size);
		
		// System.out.println("Col: "+col+". Left padding: "+paddingLeft+".");
		
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(PlacementInterface.getInstance().getSelectedWarrior().getID(), size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded and placed on the Map.");
		}
	}
	
}

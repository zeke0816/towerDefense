package gui.factories.warriors;

import exceptions.DatabaseException;
import gui.layouts.MapInterface;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import media.databases.MediaDatabase;

public class PlacedWarrior extends Label {

	public PlacedWarrior(int col, int cellSize) {
		super();
		double paddingLeft = (col+1) * cellSize;
		setPadding(new Insets(0, 0, 0, paddingLeft));
		// System.out.println("Col: "+col+". Left padding: "+paddingLeft+".");
		setPrefHeight(cellSize);
		setPrefWidth(cellSize);
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(MapInterface.getInstance().selectedWarrior().getID(), cellSize, cellSize, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded and placed on the Map.");
		}
	}
	
}

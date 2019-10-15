package gui.factories.enemies;

import exceptions.CellTakenException;
import exceptions.DatabaseException;
import exceptions.InvalidActionException;
import game.Game;
import game.characters.Enemy;
import gui.layouts.PlacementLayout;
import javafx.scene.control.Label;
import media.databases.MediaDatabase;

/**
 * Class that helps understand the movement of an Enemy on the arena and communicate such information with the logic of the game
 * @author zeke0816
 *
 */
public class PlacedEnemy extends Label {
	
	private double pixelsAdvanced;
	private Enemy enemy;

	/**
	 * Initializes a Placed Enemy with its ID, the Enemy itself and 0 pixels advanced
	 * @param id the ID of the Enemy
	 * @param e the Enemy itself
	 */
	public PlacedEnemy(String id, Enemy e) {
		super();
		
		pixelsAdvanced = 0;
		enemy = e;
		
		double size = PlacementLayout.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size);
		setTranslateX((Game.getInstance().getMap().getColumns() - 1) * size);
		
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Moves the Enemy on the arena
	 * @throws InvalidActionException when there are no Enemies on the arena
	 * @throws CellTakenException when an Enemy was trying to take a cell that had already been taken
	 */
	public void advance() throws InvalidActionException, CellTakenException {
		// System.out.println("Trying to advance.");
		if(Game.getInstance().getMap().canAdvance(enemy)) {
			double advancement = enemy.getMovementSpeed();
			double cellSize = PlacementLayout.getCellSize();
			if(getTranslateX() < cellSize) {
				advancement = cellSize;
			}
			setTranslateX(getTranslateX() - advancement);
			pixelsAdvanced += advancement;
			// System.out.println("Advanced.");
			if(pixelsAdvanced >= cellSize) {
				pixelsAdvanced = 0;
				Game.getInstance().getMap().advance(enemy);
				// System.out.println("The Enemy has been moved.");
			}
		}
	}

}

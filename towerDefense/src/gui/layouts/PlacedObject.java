package gui.layouts;

import exceptions.CellTakenException;
import exceptions.DatabaseException;
import exceptions.InvalidActionException;
import game.Game;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
import gui.layouts.PlacementLayout;
import javafx.scene.control.Label;
import media.databases.MediaDatabase;

/**
 * Class that helps understand the movement of an Enemy on the arena and communicate such information with the logic of the game
 * @author zeke0816
 *
 */
public class PlacedObject extends Label {
	
	private double pixelsAdvanced;
	private int row;
	private int col;

	/**
	 * Initializes a Placed Object with its ID, coordinates, and 0 advanced pixels
	 * @param r the row
	 * @param c the column
	 * @param id the ID of the Game Object
	 */
	public PlacedObject(int r, int c, String id) {
		super();
		
		pixelsAdvanced = 0;
		row = r;
		col = c;
		
		double size = PlacementLayout.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size);
		setTranslateX(col * size);
		
		try {
			setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Moves a Warrior on the arena
	 * @throws InvalidActionException when there are no Warriors on the arena
	 * @throws CellTakenException when a Warrior was trying to take a cell that had already been taken
	 */
	public void advance(Warrior w) throws InvalidActionException, CellTakenException {
		
	}
	
	/**
	 * Moves an Enemy on the arena
	 * @throws InvalidActionException when there are no Enemies on the arena
	 * @throws CellTakenException when an Enemy was trying to take a cell that had already been taken
	 */
	public void advance(Enemy e) throws InvalidActionException, CellTakenException {
		if(Game.getInstance().getMap().canAdvance(e)) {
			double advancement = e.getMovementSpeed();
			double cellSize = PlacementLayout.getCellSize();
			if(getTranslateX() < cellSize) {
				advancement = cellSize;
			}
			setTranslateX(getTranslateX() - advancement);
			pixelsAdvanced += advancement;
			if(pixelsAdvanced >= cellSize) {
				pixelsAdvanced = 0;
				Game.getInstance().getMap().advance(e);
				col--;
			}
		}
	}
	
	/**
	 * Moves a Item on the arena
	 * @throws InvalidActionException when there are no Items on the arena
	 * @throws CellTakenException when a Item was trying to take a cell that had already been taken
	 */
	public void advance(Item i) throws InvalidActionException, CellTakenException {
		
	}
	
	/**
	 * Gets the current position of the Placed Object relative to the base
	 * @return the current distance of the Placed Object to the base
	 */
	public int getCurrentPosition() {
		return Integer.parseInt(Double.toString((col * PlacementLayout.getCellSize()) - pixelsAdvanced).split("\\.")[0]);
	}
	
	/**
	 * Gets the row where the Game Object is located
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Gets the column where the Game Object is located
	 * @return the column
	 */
	public int getCol() {
		return col;
	}

}

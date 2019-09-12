package game;

import exceptions.CellTakenException;
import objects.GameObject;

/**
 * Class that models the arena where the characters will be placed
 * @author zeke0816
 *
 */
public class Map {
	
	protected final int rows = 8;
	protected final int cols = 16;
	protected Cell[][] arena;
	
	/**
	 * Initializes an empty arena
	 */
	public Map() {
		arena = new Cell[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				arena[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * Gets the rows of the arena
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Gets the columns of the arena
	 * @return the columns
	 */
	public int getColumns() {
		return cols;
	}
	
	/**
	 * Gets the cell at the specified row and column
	 * @param row the row
	 * @param col the column
	 * @return the cell at the row-column position
	 */
	public Cell getCell(int row, int col) {
		return arena[row][col];
	}
	
	/**
	 * Tells whether there is an enemy at a given scope from a row and column
	 * @param row the source row
	 * @param col the source column
	 * @param scope the given scope
	 * @return true if there is at least one enemy within the scope, false if there is no enemy in sight
	 */
	public boolean isThereEnemyAt(int row, int col, int scope) {
		boolean thereIs = false;
		for(int i = col; !thereIs && i < scope; i++) {
			thereIs = arena[row][i].isTaken(); // TODO: check if it is taken by an enemy
		}
		return thereIs;
	}
	
	/**
	 * Gets the enemy at a given scope from a row and column
	 * @param row the source row
	 * @param col the source column
	 * @param scope the given scope
	 * @return the enemy in sight
	 */
	public GameObject enemyAt(int row, int col, int scope) {
		GameObject object = null;
		for(int i = col; object == null && i < scope; i++) {
			object = arena[row][i].getObject();
		}
		return object;
	}
	
	/**
	 * Lets an object take the place in a cell, given the row and column
	 * @param row the row to take
	 * @param col the column to take
	 * @param object the object trying to take the cell
	 */
	public void takeCell(int row, int col, GameObject object) throws CellTakenException {
		if(arena[row][col].isTaken()) {
			throw new CellTakenException("This cell has already been taken!");
		}
		arena[row][col].setObject(object);
	}

}

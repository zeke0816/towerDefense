package game;

import java.util.HashMap;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import javafx.util.Pair;

/**
 * Class that models the arena where the characters will be placed
 * @author zeke0816
 *
 */
public class Map {
	
	protected final int rows = 8;
	protected final int cols = 16;
	protected Cell[][] arena;
	protected HashMap<GameObject, Pair<Integer, Integer>> objects;
	
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
		objects = new HashMap<GameObject, Pair<Integer, Integer>>();
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
	 * Gets the enemy at a given scope from a row and column
	 * @param row the source row
	 * @param col the source column
	 * @param scope the given scope
	 * @return the object in sight
	 */
	public GameObject getObjectAt(int row, int col, int scope) {
		GameObject object = null;
		for(int i = col; i < cols && object == null && i < scope; i++) {
			object = arena[row][i].getObject();
		}
		return object;
	}
	
	/**
	 * Gets the enemy at a given scope from a row and column looking at the left side
	 * @param row the source row
	 * @param col the source column
	 * @param scope the given scope
	 * @return the object in sight
	 */
	public GameObject getObjectAtLeft(int row, int col, int scope) {
		GameObject object = null;
		for(int i = col-1; i >= 0 && object == null && i >= (col - scope); i--) {
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
		// System.out.println(object.hashCode()); // Memory tester to see if each object is correctly cloned by the prototype
		if(arena[row][col].isTaken()) {
			throw new CellTakenException("This cell has already been taken!");
		}
		arena[row][col].setObject(object);
		Pair<Integer, Integer> coor = new Pair<Integer, Integer>(row, col);
		objects.put(object, coor);
	}
	
	/**
	 * Frees a cell from an object
	 * @param obj the object to free
	 * @return a Pair of coordinates indicating where the object was on the arena
	 * @throws InvalidActionException when the object is not on the arena
	 */
	public Pair<Integer, Integer> freeCell(GameObject obj) throws InvalidActionException {
		if(objects.isEmpty()) {
			throw new InvalidActionException("There are no Enemies on the arena.");
		}
		Pair<Integer, Integer> coordinates = objects.remove(obj);
		if(coordinates == null) {
			throw new InvalidActionException("The chosen Enemy is not on the arena.");
		}
		arena[coordinates.getKey()][coordinates.getValue()].free();
		return coordinates;
	}
	
	/**
	 * Checks whether a Game Object can advance or not
	 * @param obj the Game Object
	 * @return true if it can, false if it cannot
	 * @throws InvalidActionException when there are no Game Objects on the arena or the selected Game Object is not on the arena
	 */
	public boolean canAdvance(GameObject obj) throws InvalidActionException {
		if(objects.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		Pair<Integer, Integer> coordinates = objects.get(obj);
		if(coordinates == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		if(coordinates.getValue() == 0) {
			return false; // Reached the end, this should never happen since the game would be over by now. TODO: remove this check
		}
		GameObject nextCellObject = getObjectAtLeft(coordinates.getKey(), coordinates.getValue(), 1);
		return nextCellObject == null;
	}
	
	/**
	 * Moves a Game Object one cell to the left. Assumes it has been checked whether this is possible
	 * @param obj the Game Object
	 * @throws InvalidActionException when there are no Game Objects on the arena or the selected Game Object is not on the arena
	 * @throws CellTakenException if the next cell is already taken
	 */
	public void advance(GameObject obj) throws InvalidActionException, CellTakenException {
		if(objects.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		Pair<Integer, Integer> coordinates = objects.get(obj);
		if(coordinates == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		objects.put(obj, new Pair<Integer, Integer>(coordinates.getKey(), coordinates.getValue()-1));
		freeCell(obj);
		takeCell(coordinates.getKey(), coordinates.getValue()-1, obj);
		if(coordinates.getValue()-1 == 0) {
			Game.getInstance().end(); // GAME OVER
		}
		// System.out.println("X: "+(coordinates.getValue()-1));
		// System.out.println("Y: "+coordinates.getKey());
	}

}

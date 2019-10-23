package game;

import java.util.HashMap;
import java.util.Map.Entry;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.objects.GameObject;
import javafx.util.Pair;

/**
 * Class that models the arena where the characters will be placed
 * @author zeke0816
 *
 */
public class Map {
	
	protected final int rows = 6;
	protected final int cols = 16;
	protected Cell[][] arena;
	protected HashMap<GameObject, Pair<Integer, Integer>> positions;
	
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
		positions = new HashMap<GameObject, Pair<Integer, Integer>>();
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
	 * Gets the game object at the specified row and column
	 * @param row the row
	 * @param col the column
	 * @return the game object at the row-column position
	 */
	public GameObject getObjectAt(int row, int col) {
		return getCell(row, col).getObject();
	}
	
	/**
	 * Gets a copy of the current positions of all Game Objects
	 * @return the copy of the positions
	 */
	public HashMap<GameObject, Pair<Integer, Integer>> getPositions(){
		HashMap<GameObject, Pair<Integer, Integer>> copy = new HashMap<GameObject, Pair<Integer, Integer>>();
		for(Entry<GameObject, Pair<Integer, Integer>> entry: positions.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}
		return copy;
	}
	
	/**
	 * Lets an object take the place in a cell, given the row and column
	 * @param row the row to take
	 * @param col the column to take
	 * @param object the object trying to take the cell
	 */
	public void takeCell(int row, int col, GameObject object) throws CellTakenException {
		// System.out.println(object.hashCode()); // Memory tester to see if each object is correctly cloned by the prototype
		if(getCell(row, col).isTaken()) {
			throw new CellTakenException("This cell has already been taken!");
		}
		getCell(row, col).setObject(object);
		Pair<Integer, Integer> coor = new Pair<Integer, Integer>(row, col);
		positions.put(object, coor);
	}
	
	/**
	 * Frees a cell from an object
	 * @param obj the object to free
	 * @return a Pair of coordinates indicating where the object was on the arena
	 * @throws InvalidActionException when the object is not on the arena
	 */
	public void freeCell(GameObject obj) throws InvalidActionException {
		if(positions.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		Pair<Integer, Integer> coordinates = positions.remove(obj);
		if(coordinates == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		getCell(coordinates.getKey(), coordinates.getValue()).free();
	}
	
	/**
	 * Checks whether a Game Object can advance or not
	 * @param obj the Game Object
	 * @return true if it can, false if it cannot
	 * @throws InvalidActionException when there are no Game Objects on the arena or the selected Game Object is not on the arena
	 */
	public boolean canAdvance(GameObject obj) throws InvalidActionException {
		if(positions.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		Pair<Integer, Integer> coordinates = positions.get(obj);
		if(coordinates == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		GameObject nextCellObject = getObjectAt(coordinates.getKey(), coordinates.getValue() - 1);
		return nextCellObject == null;
	}
	
	/**
	 * Moves a Game Object one cell to the left. Assumes it has been checked whether this is possible
	 * @param obj the Game Object
	 * @throws InvalidActionException when there are no Game Objects on the arena or the selected Game Object is not on the arena
	 * @throws CellTakenException if the next cell is already taken
	 */
	public void advance(GameObject obj) throws InvalidActionException, CellTakenException {
		if(positions.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		Pair<Integer, Integer> coordinates = positions.get(obj);
		if(coordinates == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		freeCell(obj);
		positions.put(obj, new Pair<Integer, Integer>(coordinates.getKey(), coordinates.getValue()-1));
		takeCell(coordinates.getKey(), coordinates.getValue()-1, obj);
		if(coordinates.getValue()-1 == 0) {
			Game.getInstance().end(); // GAME OVER
		}
		// System.out.println("X: "+(coordinates.getValue()-1));
		// System.out.println("Y: "+coordinates.getKey());
	}

}

package game;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public static final int cellSize = 64;
	public static final double limitFactor = .6;
	protected final int lanes = 6;
	protected final int distance = 13 * cellSize;
	protected Cell[][] arena;
	protected HashMap<GameObject, Pair<Integer, Integer>> positions;
	
	/**
	 * Initializes an empty arena
	 */
	public Map() {
		arena = new Cell[lanes][distance];
		for(int i = 0; i < lanes; i++) {
			for(int j = 0; j < distance; j++) {
				arena[i][j] = new Cell();
				// if this is the last lane
				if(i == lanes - 1) {
					arena[i][j].takeDoubleObjects(false);
				}
			}
		}
		positions = new HashMap<GameObject, Pair<Integer, Integer>>();
	}
	
	/**
	 * Flushes the map
	 */
	public void flush(){
		positions.clear();
	}
	
	/**
	 * Gets the rows of the arena
	 * @return the rows
	 */
	public int getLanes() {
		return lanes;
	}
	
	/**
	 * Gets the columns of the arena
	 * @return the columns
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Gets the cell at the specified lane and distance
	 * @param l the lane
	 * @param d the distance
	 * @return the cell at the lane-distance position
	 */
	public Cell getCell(int l, int d) {
		return arena[l][d];
	}
	
	/**
	 * Gets the game object at the specified lane and distance
	 * @param l the lane
	 * @param d the distance
	 * @return the game object at the lane-distance position
	 */
	public GameObject getObjectAt(int l, int d) {
		return getCell(l, d).getObject();
	}
	
	/**
	 * Gets a copy of the current positions of all Game Objects
	 * @return the copy of the positions
	 */
	public ArrayList<GameObject> getGameObjectList(){
		ArrayList<GameObject> list = new ArrayList<GameObject>();
		for(GameObject object: positions.keySet()) {
			list.add(object);
		}
		return list;
	}
	
	/**
	 * Lets an object take the place in a cell, given the lane and distance
	 * @param l the lane to take
	 * @param d the distance to take
	 * @param object the object trying to take the cell
	 * @throws InvalidActionException 
	 */
	public void takeCell(GameObject object) throws CellTakenException, InvalidActionException {
		// System.out.println(object.hashCode()); // Memory tester to see if each object is correctly cloned by the prototype
		int l = object.getLane();
		int d = object.getDistance();
		Cell cell = getCell(l, d);
		if(cell.isTaken()) {
			throw new CellTakenException("This cell has already been taken!");
		}
		if(!cell.canTakeDoubleObjects() && object.takesTwoCells()) {
			throw new InvalidActionException("This object cannot be placed on this cell.");
		}
		cell.setObject(object);
		Pair<Integer, Integer> coor = new Pair<Integer, Integer>(l, d);
		positions.put(object, coor);
		if(object.takesTwoCells()) {
			getCell(l + 1, d).setObject(object);
		}
	}
	
	/**
	 * Frees a cell from an object
	 * @param obj the object to free
	 * @return a Pair of coordinates indicating where the object was on the arena
	 * @throws InvalidActionException when the object is not on the arena
	 */
	public void freeCell(GameObject object) throws InvalidActionException {
		if(positions.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		if(positions.remove(object) == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		getCell(object.getLane(), object.getDistance()).free();
		if(object.takesTwoCells()) {
			getCell(object.getLane() + 1, object.getDistance()).free();
		}
	}
	
	/**
	 * Checks whether a Game Object can advance or not
	 * @param obj the Game Object
	 * @return true if it can, false if it cannot
	 * @throws InvalidActionException when there are no Game Objects on the arena or the selected Game Object is not on the arena
	 */
	public boolean canAdvance(GameObject object) throws InvalidActionException {
		if(positions.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		if(positions.get(object) == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		GameObject nextCellObject = getObjectAt(object.getLane(), object.getDistance() - 1);
		return nextCellObject == null;
	}
	
	/**
	 * Moves a Game Object one cell to the left. Assumes it has been checked whether this is possible
	 * @param obj the Game Object
	 * @throws InvalidActionException when there are no Game Objects on the arena or the selected Game Object is not on the arena
	 * @throws CellTakenException if the next cell is already taken
	 */
	public void advance(GameObject object) throws InvalidActionException, CellTakenException {
		if(positions.isEmpty()) {
			throw new InvalidActionException("There are no Game Objects on the arena.");
		}
		if(positions.get(object) == null) {
			throw new InvalidActionException("The chosen Game Object is not on the arena.");
		}
		freeCell(object);
		object.setDistance(object.getDistance()-1);
		int l = object.getLane();
		int d = object.getDistance();
		positions.put(object, new Pair<Integer, Integer>(l, d));
		takeCell(object);
		if(d == 0) {
			Game.getInstance().end(); // GAME OVER
		}
		// System.out.println("X: "+(coordinates.getValue()-1));
		// System.out.println("Y: "+coordinates.getKey());
	}

}

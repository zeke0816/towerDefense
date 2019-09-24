package gui.controls;

import gui.layouts.PlacementLayout;
import javafx.scene.control.Button;
import javafx.util.Pair;

/**
 * Class that helps with the creation of the cells and storing important information for the logic of the game
 * @author zeke0816
 *
 */
public class CellButton extends Button {
	
	protected int x;
	protected int y;
	
	/**
	 * Initializes the Cell Interface as a Button
	 */
	public CellButton() {
		super();
		setVisible(true);
		setBackground(null);
		setOpacity(.6);
		
		double size = PlacementLayout.getCellSize();
		setMinSize(size, size);
		setMaxSize(size, size);
		setPrefSize(size, size);
	}
	
	/**
	 * Sets the coordinates of the cell
	 * @param xAxis the x coordinate
	 * @param yAxis the y coordinate
	 */
	public void setCoordinates(int xAxis, int yAxis) {
		x = xAxis;
		y = yAxis;
	}
	
	/**
	 * Gets the coordinates of the cell as a Pair
	 * @return a Pair of coordinates, the key is X and the value is Y
	 */
	public Pair<Integer, Integer> getCoordinates() {
		return new Pair<Integer, Integer>(x, y);
	}
	
	/**
	 * Gets the x coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

}

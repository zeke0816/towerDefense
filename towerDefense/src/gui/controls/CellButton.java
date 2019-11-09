package gui.controls;

import exceptions.InvalidActionException;
import exceptions.UnselectedObjectException;
import game.Cell;
import game.Game;
import game.Map;
import game.objects.GameObject;
import gui.layouts.PlacementLayout;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.util.Pair;
import visitors.PlacementVisitor;

/**
 * Class that helps with the creation of the cells and storing important information for the logic of the game
 * @author zeke0816
 *
 */
public class CellButton extends Button {
	
	protected int x;
	protected int y;
	private EventHandler<MouseEvent> placementAllowanceListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if(placementAllowed()) {
				setBackground(new Background(new BackgroundFill(Paint.valueOf("#9ae39c"), null, null)));
			} else {
				setBackground(new Background(new BackgroundFill(Paint.valueOf("#f05959"), null, null)));
			}
		}
		
	};
	private EventHandler<MouseEvent> placementDismissedListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			setBackground(null);
		}
		
	};
	private EventHandler<MouseEvent> placementListener = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			try {
				if(!placementAllowed()) {
					throw new InvalidActionException("The selected Object cannot be placed here!");
				}
				PlacementLayout layout = PlacementLayout.getInstance();
				if(!layout.objectSelected()) {
					throw new UnselectedObjectException("No Object has been selected!");
				}
				int distance = x * Map.cellSize;
				PlacementVisitor placement = new PlacementVisitor(y, distance);
				if(layout.objectSelected()) {
					GameObject object = layout.getSelectedObject().cloneObject();
					object.setLane(y);
					object.setDistance(distance);
					object.accept(placement);
				}
			} catch(UnselectedObjectException | InvalidActionException e) {
				System.out.println(e.getMessage());
			}
		}
		
	};
	
	/**
	 * Initializes the Cell Interface as a Button
	 */
	public CellButton(int yAxis, int xAxis) {
		super();
		setVisible(true);
		setBackground(null);
		setOpacity(.6);
		
		x = xAxis;
		y = yAxis;
		
		setOnMouseClicked(placementListener);
		setOnMouseEntered(placementAllowanceListener);
		setOnMouseExited(placementDismissedListener);
		
		double size = Map.cellSize;
		setMinSize(size, size);
		setMaxSize(size, size);
		setPrefSize(size, size);
	}
	
	protected boolean placementAllowed() {
		boolean allowed = false;
		PlacementLayout layout = PlacementLayout.getInstance();
		if(layout.objectSelected()) {
			boolean takesTwoCells = false;
			if(layout.objectSelected()) {
				takesTwoCells = layout.getSelectedObject().getObject().takesTwoCells();
			}
			Map map = Game.getInstance().getMap();
			double placementLimit = map.getDistance() * Map.limitFactor;
			int distance = x * Map.cellSize;
			Cell cell = map.getCell(y, distance);
			allowed = (distance < placementLimit) && (!takesTwoCells || (takesTwoCells && cell.canTakeDoubleObjects()));
		}
		return allowed;
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

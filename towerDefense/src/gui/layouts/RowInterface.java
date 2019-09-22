package gui.layouts;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class RowInterface extends StackPane {
	
	public RowInterface() {
		setAlignment(Pos.CENTER_LEFT);
		
		double size = PlacementInterface.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size * Game.getInstance().getMap().getColumns());
	}

}

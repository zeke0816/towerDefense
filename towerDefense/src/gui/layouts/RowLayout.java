package gui.layouts;

import game.Game;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class RowLayout extends StackPane {
	
	public RowLayout() {
		setAlignment(Pos.CENTER_LEFT);
		
		double size = PlacementLayout.getCellSize();
		setPrefHeight(size);
		setPrefWidth(size * Game.getInstance().getMap().getColumns());
	}

}

package gui.layouts;

import game.Game;
import game.Map;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class RowLayout extends StackPane {
	
	public RowLayout() {
		setAlignment(Pos.CENTER_LEFT);

		Map map = Game.getInstance().getMap();
		setPrefHeight(Map.cellSize * map.getLanes());
		setPrefWidth(map.getDistance());
	}

}

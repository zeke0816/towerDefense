package gui.factories.prototypes.characters.warriors;

import exceptions.DatabaseException;
import game.Map;
import gui.factories.prototypes.ObjectPrototype;
import media.MediaDatabase;

public class TophPrototype extends ObjectPrototype {

	public TophPrototype() {
		super();
		
		placingButton.setOnMouseClicked(null);
		placingButton.setBorder(null);
		placingButton.setPrefSize(Map.cellSize * 3, Map.cellSize * 3);
		
        try {
			placingButton.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia("toph", Map.cellSize * 3, Map.cellSize * 3, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
	}

}

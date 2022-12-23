package gui.factories.prototypes.characters.warriors;

import game.Map;
import media.MediaDatabase;
import exceptions.DatabaseException;
import javafx.scene.layout.Background;
import game.objects.characters.warriors.AgentP;
import gui.factories.prototypes.ObjectPrototype;

public class AgentPPrototype extends ObjectPrototype {

	public AgentPPrototype() {
		super();

		object = new AgentP();

        try {
        	Background bg = MediaDatabase.getInstance().getImageBackgroundMedia(object.getID()+"_profile", Map.cellSize, Map.cellSize, true, false);
        	Background disabled = MediaDatabase.getInstance().getImageBackgroundMedia(object.getID()+"_profile_disabled", Map.cellSize, Map.cellSize, true, false);
			placingButton.setBackground(bg);
			profileLabel.setBackground(bg);
			disabledLabel.setBackground(disabled);
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        placingButton.setPrototype(this);
        buyingButton.setPrototype(this);
        sellingButton.setPrototype(this);
        buyPlaceButton.setPrototype(this);
        buyingButton.setText("$ "+object.getPrice());
        sellingButton.setText("$ "+object.getSellValue());
	}

}

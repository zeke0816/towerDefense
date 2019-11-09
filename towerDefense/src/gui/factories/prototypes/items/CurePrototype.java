package gui.factories.prototypes.items;

import exceptions.DatabaseException;
import game.Map;
import game.objects.items.charm.permanent.Cure;
import gui.factories.prototypes.ObjectPrototype;
import javafx.scene.layout.Background;
import media.MediaDatabase;

public class CurePrototype extends ObjectPrototype {

	public CurePrototype() {
		super();
		
		object = new Cure();

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
        buyPlaceButton.setPrototype(this);
        buyingButton.setText("$ "+object.getPrice());
	}

}

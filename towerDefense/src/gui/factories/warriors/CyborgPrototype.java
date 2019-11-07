package gui.factories.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.Cyborg;
import javafx.scene.layout.Background;
import media.MediaDatabase;

public class CyborgPrototype extends WarriorPrototype {

	public CyborgPrototype() {
		super();
		
		id = "cyborg";
		name = "Cyborg";
		playsSound = true;

        try {
        	Background bg = MediaDatabase.getInstance().getImageBackgroundMedia(id+"_profile", size, size, true, false);
        	Background disabled = MediaDatabase.getInstance().getImageBackgroundMedia(id+"_profileDisabled", size, size, true, false);
			placingButton.setBackground(bg);
			profileLabel.setBackground(bg);
			disabledLabel.setBackground(disabled);
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        placingButton.setPrototype(this);
        buyingButton.setPrototype(this);
        buyPlaceButton.setPrototype(this);
        warrior = new Cyborg();
        buyingButton.setText("$ "+warrior.getPrice());
	}

}

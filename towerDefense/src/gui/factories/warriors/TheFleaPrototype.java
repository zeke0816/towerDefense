package gui.factories.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.TheFlea;
import javafx.scene.layout.Background;
import media.MediaDatabase;

public class TheFleaPrototype extends WarriorPrototype {

	public TheFleaPrototype() {
		super();
		
		id = "theFlea";
		name = "The Flea";
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
        warrior = new TheFlea();
        buyingButton.setText("$ "+warrior.getPrice());
	}


}

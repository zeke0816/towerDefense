package gui.factories.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.Gary;
import javafx.scene.layout.Background;
import media.MediaDatabase;

public class GaryPrototype extends WarriorPrototype {

	public GaryPrototype() {
		super();
		
		id = "gary";
		name = "Gary";
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
        warrior = new Gary();
        buyingButton.setText("$ "+warrior.getPrice());
	}

}

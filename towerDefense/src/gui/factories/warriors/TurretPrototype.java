package gui.factories.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.Turret;
import javafx.scene.layout.Background;
import media.databases.MediaDatabase;

public class TurretPrototype extends WarriorPrototype {

	public TurretPrototype() {
		super();
		
		id = "turret";
		name = "Turret";
		playsSound = false;
		
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
        warrior = new Turret();
        buyingButton.setText("$ "+warrior.getPrice());
	}

}

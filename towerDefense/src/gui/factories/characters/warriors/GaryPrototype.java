package gui.factories.characters.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.Gary;
import media.databases.MediaDatabase;

public class GaryPrototype extends WarriorPrototype {

	public GaryPrototype() {
		super();
		
		id = "gary";
		name = "Gary";
		playsSound = true;
		
		nameLabel.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new Gary();
        priceLabel.setText("$ "+warrior.getPrice());
	}

}

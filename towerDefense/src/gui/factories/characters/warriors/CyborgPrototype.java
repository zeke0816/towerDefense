package gui.factories.characters.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.Cyborg;
import media.databases.MediaDatabase;

public class CyborgPrototype extends WarriorPrototype {

	public CyborgPrototype() {
		super();
		
		id = "cyborg";
		name = "Cyborg";
		playsSound = true;
		
		nameLabel.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new Cyborg();
        priceLabel.setText("$ "+warrior.getPrice());
	}

}

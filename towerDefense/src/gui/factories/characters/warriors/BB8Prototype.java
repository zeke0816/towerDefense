package gui.factories.characters.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.BB8;
import media.databases.MediaDatabase;

public class BB8Prototype extends WarriorPrototype {

	public BB8Prototype() {
		super();
		
		id = "bb8";
		name = "BB8";
		playsSound = false;
		
		nameLabel.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new BB8();
        priceLabel.setText("$ "+warrior.getPrice());
	}

}

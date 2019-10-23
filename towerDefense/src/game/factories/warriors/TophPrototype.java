package game.factories.warriors;

import exceptions.DatabaseException;
import media.databases.MediaDatabase;

public class TophPrototype extends WarriorPrototype {

	public TophPrototype() {
		super();
		
		id = "toph";
		name = "Toph";
		playsSound = false;
		
		nameLabel.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setOnMouseClicked(null);
	}

}

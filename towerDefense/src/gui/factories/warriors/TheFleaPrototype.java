package gui.factories.warriors;

import exceptions.DatabaseException;
import game.characters.warriors.TheFlea;
import media.databases.MediaDatabase;

public class TheFleaPrototype extends WarriorPrototype {

	public TheFleaPrototype() {
		super();
		
		id = "theFlea";
		name = "The Flea";
		playsSound = true;
		
		label.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new TheFlea();
	}


}

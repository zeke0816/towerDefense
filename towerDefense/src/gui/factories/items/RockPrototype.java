package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.killable.Rock;
import media.databases.MediaDatabase;

public class RockPrototype extends ItemPrototype {

	public RockPrototype() {
		id = "rock";
		name = "Rock";
		playsSound = false;

        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        button.setItem(this);
		item = new Rock();
	}

}

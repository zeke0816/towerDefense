package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.killable.Tank;
import media.databases.MediaDatabase;

public class TankPrototype extends ItemPrototype {

	public TankPrototype() {
		id = "tank";
		name = "Tank";
		playsSound = false;

        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        button.setItem(this);
		item = new Tank();
	}

}

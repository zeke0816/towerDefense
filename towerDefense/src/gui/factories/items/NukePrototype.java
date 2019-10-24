package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.killable.Nuke;
import media.databases.MediaDatabase;

public class NukePrototype extends ItemPrototype {

	public NukePrototype() {
		id = "nuke";
		name = "Nuke";
		playsSound = false;
		
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        button.setItem(this);
		item = new Nuke();
	}

}

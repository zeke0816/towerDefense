package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.charm.permanent.Cure;
import media.databases.MediaDatabase;

public class CurePrototype extends ItemPrototype {

	public CurePrototype() {
		id = "cure";
		key = "_cured";
		name = "Cure";
		playsSound = false;

        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        nameLabel.setText(name);
        button.setItem(this);
		item = new Cure();
	}

}

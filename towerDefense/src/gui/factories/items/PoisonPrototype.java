package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.charm.temporary.Poison;
import media.databases.MediaDatabase;

public class PoisonPrototype extends ItemPrototype {

	public PoisonPrototype() {
		id = "poison";
		name = "Poison";
		playsSound = false;

        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        nameLabel.setText(name);
        button.setItem(this);
		item = new Poison();
	}

}

package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.charm.temporary.Shield;
import media.databases.MediaDatabase;

public class ShieldPrototype extends ItemPrototype {

	public ShieldPrototype() {
		id = "shield";
		name = "Shield";
		playsSound = false;

        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        nameLabel.setText(name);
        button.setItem(this);
		item = new Shield();
	}

}

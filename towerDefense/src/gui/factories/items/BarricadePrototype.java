package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.killable.Barricade;
import media.databases.MediaDatabase;

public class BarricadePrototype extends ItemPrototype {

	public BarricadePrototype() {
		id = "barricade";
		name = "Barricade";
		playsSound = false;

        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Item's graphics could not be loaded.");
		}
        nameLabel.setText(name);
        button.setItem(this);
		item = new Barricade();
	}

}

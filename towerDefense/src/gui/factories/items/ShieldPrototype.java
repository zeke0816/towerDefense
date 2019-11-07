package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.charm.temporary.Shield;
import javafx.scene.layout.Background;
import media.MediaDatabase;

public class ShieldPrototype extends ItemPrototype {

	public ShieldPrototype() {
		id = "shield";
		key = "_shielded";
		name = "Shield";
		playsSound = false;

        try {
        	Background bg = MediaDatabase.getInstance().getImageBackgroundMedia(id+"_profile", size, size, true, false);
        	Background disabled = MediaDatabase.getInstance().getImageBackgroundMedia(id+"_profileDisabled", size, size, true, false);
			placingButton.setBackground(bg);
			profileLabel.setBackground(bg);
			disabledLabel.setBackground(disabled);
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        placingButton.setPrototype(this);
        buyingButton.setPrototype(this);
        buyPlaceButton.setPrototype(this);
		item = new Shield();
        buyingButton.setText("$ "+item.getPrice());
	}

}

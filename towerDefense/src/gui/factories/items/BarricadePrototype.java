package gui.factories.items;

import exceptions.DatabaseException;
import game.objects.items.killable.Barricade;
import javafx.scene.layout.Background;
import media.MediaDatabase;

public class BarricadePrototype extends ItemPrototype {

	public BarricadePrototype() {
		id = "barricade";
		name = "Barricade";
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
		item = new Barricade();
        buyingButton.setText("$ "+item.getPrice());
	}

}

package game.factories.warriors;

import exceptions.DatabaseException;
import game.characters.warriors.Turret;
import media.databases.MediaDatabase;

public class TurretPrototype extends WarriorPrototype {

	public TurretPrototype() {
		super();
		
		id = "turret";
		name = "Turret";
		playsSound = false;
		
		label.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new Turret();
	}

}

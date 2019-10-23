package gui.factories.items;

import game.objects.items.charm.permanent.Cure;

public class CurePrototype extends ItemPrototype {

	public CurePrototype() {
		id = "cure";
		name = "Cure";
		playsSound = false;
		
		item = new Cure();
	}

}

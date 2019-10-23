package gui.factories.items;

import game.objects.items.charm.temporary.Poison;

public class PoisonPrototype extends ItemPrototype {

	public PoisonPrototype() {
		id = "poison";
		name = "Poison";
		playsSound = false;
		
		item = new Poison();
	}

}

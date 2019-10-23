package gui.factories.items;

import game.objects.items.charm.temporary.Shield;

public class ShieldPrototype extends ItemPrototype {

	public ShieldPrototype() {
		id = "shield";
		name = "Shield";
		playsSound = false;
		
		item = new Shield();
	}

}

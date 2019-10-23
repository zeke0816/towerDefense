package gui.factories.items;

import game.objects.items.killable.Tank;

public class TankPrototype extends ItemPrototype {

	public TankPrototype() {
		id = "tank";
		name = "Tank";
		playsSound = false;
		
		item = new Tank();
	}

}

package gui.factories.items;

import game.objects.items.killable.Barricade;

public class BarricadePrototype extends ItemPrototype {

	public BarricadePrototype() {
		id = "barricade";
		name = "Barricade";
		playsSound = false;
		
		item = new Barricade();
	}

}

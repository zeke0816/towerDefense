package gui.factories.items;

import game.objects.items.killable.Rock;

public class RockPrototype extends ItemPrototype {

	public RockPrototype() {
		id = "rock";
		name = "Rock";
		playsSound = false;
		
		item = new Rock();
	}

}

package gui.factories.items;

import game.objects.items.killable.Nuke;

public class NukePrototype extends ItemPrototype {

	public NukePrototype() {
		id = "nuke";
		name = "Nuke";
		playsSound = false;
		
		item = new Nuke();
	}

}

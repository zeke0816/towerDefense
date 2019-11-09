package gui.factories.prototypes.characters.enemies;

import game.objects.characters.enemies.Doof;
import gui.factories.prototypes.ObjectPrototype;

public class DoofPrototype extends ObjectPrototype {
	
	public DoofPrototype() {
		super();
		
		object = new Doof();
	}
	
}

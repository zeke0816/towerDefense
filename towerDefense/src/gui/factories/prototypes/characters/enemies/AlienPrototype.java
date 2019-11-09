package gui.factories.prototypes.characters.enemies;

import game.objects.characters.enemies.Alien;
import gui.factories.prototypes.ObjectPrototype;

public class AlienPrototype extends ObjectPrototype {
	
	public AlienPrototype() {
		super();
		
		object = new Alien();
	}
	
}

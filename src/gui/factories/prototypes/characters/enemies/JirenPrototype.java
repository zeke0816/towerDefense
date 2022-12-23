package gui.factories.prototypes.characters.enemies;

import game.objects.characters.enemies.Jiren;
import gui.factories.prototypes.ObjectPrototype;

public class JirenPrototype extends ObjectPrototype {
	
	public JirenPrototype() {
		super();
		
		object = new Jiren();
	}

}

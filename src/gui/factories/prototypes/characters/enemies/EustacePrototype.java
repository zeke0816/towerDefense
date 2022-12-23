package gui.factories.prototypes.characters.enemies;

import game.objects.characters.enemies.Eustace;
import gui.factories.prototypes.ObjectPrototype;

public class EustacePrototype extends ObjectPrototype {
	
	public EustacePrototype() {
		super();
		
		object = new Eustace();
	}
	
}

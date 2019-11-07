package gui.factories.enemies;

import game.objects.characters.enemies.Jiren;

public class JirenPrototype extends EnemyPrototype {
	
	public JirenPrototype() {
		id = "jiren";
		name = "Jiren";
		playsSound = false;
		
		enemy = new Jiren();
	}

}

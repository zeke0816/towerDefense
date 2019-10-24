package gui.factories.enemies;

import game.objects.characters.enemies.Eustace;

public class EustacePrototype extends EnemyPrototype {
	
	public EustacePrototype() {
		id = "eustace";
		name = "Eustace";
		playsSound = false;
		
		enemy = new Eustace();
	}
	
}

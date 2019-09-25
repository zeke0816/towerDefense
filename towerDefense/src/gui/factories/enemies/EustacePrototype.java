package gui.factories.enemies;

import characters.enemies.Eustace;

public class EustacePrototype extends EnemyPrototype {
	public EustacePrototype() {

		id = "eustace";
		name = "Eustace";
		playsSound = true;
		
		enemy = new Eustace();
	}
}

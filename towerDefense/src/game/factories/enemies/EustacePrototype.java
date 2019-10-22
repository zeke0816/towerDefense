package game.factories.enemies;

import game.characters.enemies.Eustace;

public class EustacePrototype extends EnemyPrototype {
	public EustacePrototype() {

		id = "eustace";
		name = "Eustace";
		playsSound = true;
		
		enemy = new Eustace();
	}
}

package game.factories.enemies;

import game.characters.enemies.Doof;

public class DoofPrototype extends EnemyPrototype {
	public DoofPrototype() {

		id = "doof";
		name = "Dr. Doofenshmirtz";
		playsSound = false;
		
		enemy = new Doof();
	}
}

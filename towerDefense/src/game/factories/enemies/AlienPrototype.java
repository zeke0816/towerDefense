package game.factories.enemies;

import game.characters.enemies.Alien;

public class AlienPrototype extends EnemyPrototype {
	public AlienPrototype() {

		id = "alien";
		name = "Alien";
		playsSound = false;
		
		enemy = new Alien();
	}
}

package gui.factories.enemies;

import characters.enemies.Alien;

public class AlienPrototype extends EnemyPrototype {
	public AlienPrototype() {

		id = "alien";
		name = "Alien";
		playsSound = false;
		
		enemy = new Alien();
	}
}

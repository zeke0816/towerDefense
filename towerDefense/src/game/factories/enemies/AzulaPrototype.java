package game.factories.enemies;

import game.characters.enemies.Azula;

public class AzulaPrototype extends EnemyPrototype{
	public AzulaPrototype() {

		id = "azula";
		name = "Azula";
		playsSound = false;
		
		enemy = new Azula();
	}
}

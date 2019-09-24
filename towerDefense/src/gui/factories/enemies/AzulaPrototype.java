package gui.factories.enemies;

import characters.enemies.Azula;

public class AzulaPrototype extends EnemyPrototype{
	public AzulaPrototype() {

		id = "azula";
		name = "Azula";
		playsSound = true;
		
		enemy = new Azula();
	}
}

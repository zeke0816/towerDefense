package gui.factories.enemies;

import game.objects.characters.enemies.Azula;

public class AzulaPrototype extends EnemyPrototype{
	public AzulaPrototype() {

		id = "azula";
		name = "Azula";
		playsSound = false;
		
		enemy = new Azula();
	}
}

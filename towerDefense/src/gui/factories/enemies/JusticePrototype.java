package gui.factories.enemies;

import characters.enemies.Justice;

public class JusticePrototype extends EnemyPrototype {
	public JusticePrototype() {

		id = "justice";
		name = "Justice";
		playsSound = true;
		
		enemy = new Justice();
	}
}

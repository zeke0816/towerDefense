package game.characters.enemies;

import game.characters.Enemy;

public class Azula extends Enemy {
	public Azula() {
		points = 1000;
		movementSpeed = 8;
	}
	
	private Azula(Azula target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Azula(this);
	}
}

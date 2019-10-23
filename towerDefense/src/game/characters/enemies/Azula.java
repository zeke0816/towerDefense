package game.characters.enemies;

import game.characters.Enemy;

public class Azula extends Enemy {
	public Azula() {
		scope = 7;
		life = 1000;
		points = 1000;
		strength = 1200;
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

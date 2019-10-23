package game.characters.enemies;

import game.characters.Enemy;

public class Doof extends Enemy {
	public Doof() {
		scope = 2;
		life = 1000;
		points = 2000;
		strength = 200;
		movementSpeed = 2;
	}
	
	private Doof(Doof target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(this);
	}
}

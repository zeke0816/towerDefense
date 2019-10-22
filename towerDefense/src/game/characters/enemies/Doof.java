package game.characters.enemies;

import game.characters.Enemy;

public class Doof extends Enemy{
	public Doof() {
		points = 2000;
		movementSpeed = 2;
		scope = 2;
		life = 1000;
	}
	
	private Doof(Doof target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(this);
	}
}

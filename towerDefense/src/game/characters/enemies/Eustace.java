package game.characters.enemies;

import game.characters.Enemy;

public class Eustace extends Enemy {
	public Eustace() {
		points = 1600;
		movementSpeed = 1;
		scope = 1;
		life = 1000;
	}
	
	private Eustace(Eustace target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Eustace(this);
	}
}

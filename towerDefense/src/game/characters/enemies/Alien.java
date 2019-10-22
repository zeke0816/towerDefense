package game.characters.enemies;

import game.characters.Enemy;

public class Alien extends Enemy {
	public Alien() {
		points = 300;
		movementSpeed = 4;
		scope = 3;
		life = 1000;
	}
	
	private Alien(Alien target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Alien(this);
	}
}

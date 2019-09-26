package characters.enemies;

import characters.Enemy;

public class Alien extends Enemy {
	public Alien() {
		points = 300;
		movementSpeed = 4;
	}
	
	private Alien(Alien target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Alien(this);
	}
}

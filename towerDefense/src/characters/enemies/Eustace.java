package characters.enemies;

import characters.Enemy;

public class Eustace extends Enemy {
	public Eustace() {
		points = 1600;
		movementSpeed = 1;
	}
	
	private Eustace(Eustace target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Eustace(this);
	}
}

package characters.enemies;

import characters.Enemy;

public class Azula extends Enemy {
	public Azula() {
		points = 1000;
		movementSpeed = 8;
	}
	
	protected Azula(Azula target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Azula(this);
	}
}

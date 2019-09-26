package characters.enemies;

import characters.Enemy;

public class Doof extends Enemy{
	public Doof() {
		points = 2000;
		movementSpeed = 2;
	}
	
	protected Doof(Doof target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(this);
	}
}

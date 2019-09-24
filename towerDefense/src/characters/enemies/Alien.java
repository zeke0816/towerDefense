package characters.enemies;

import characters.Enemy;

public class Alien extends Enemy {
	public Alien() {
		
	}
	
	protected Alien(Alien target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Alien(); //TODO implementar clone
	}
}

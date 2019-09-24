package characters.enemies;

import characters.Enemy;

public class Azula extends Enemy {
	public Azula() {
		
	}
	
	protected Azula(Azula target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Azula(); //TODO implementar clone
	}
}

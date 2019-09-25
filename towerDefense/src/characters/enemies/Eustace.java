package characters.enemies;

import characters.Enemy;

public class Eustace extends Enemy {
	public Eustace() {
		
	}
	
	protected Eustace(Eustace target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Eustace(this);
	}
}

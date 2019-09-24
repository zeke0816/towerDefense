package characters.enemies;

import characters.Enemy;

public class Doof extends Enemy{
	public Doof() {
		
	}
	
	protected Doof(Doof target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(); //TODO implementar clone
	}
}

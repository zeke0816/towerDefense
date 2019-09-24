package characters.enemies;

import characters.Enemy;

public class Justice extends Enemy {
	public Justice() {
		
	}
	
	protected Justice(Justice target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(); //TODO implementar clone
	}
}

package game.characters.warriors;

import game.characters.Warrior;

public class Turret extends Warrior {
	
	public Turret() {
		scope = 8;
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private Turret(Turret target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Turret(this);
	}

}

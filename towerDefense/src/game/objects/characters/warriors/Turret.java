package game.objects.characters.warriors;

import game.objects.characters.Warrior;

public class Turret extends Warrior {
	
	public Turret() {
		scope = 8;
		life = 1200;
		price = 1300;
		strength = 500;
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

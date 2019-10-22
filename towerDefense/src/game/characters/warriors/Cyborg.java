package game.characters.warriors;

import game.characters.Warrior;

public class Cyborg extends Warrior {
	
	public Cyborg() {
		scope = 7;
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private Cyborg(Cyborg target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Cyborg(this);
	}

}

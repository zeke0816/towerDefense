package game.characters.warriors;

import game.characters.Warrior;

public class Gary extends Warrior {
	
	public Gary() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private Gary(Gary target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Gary(this);
	}

}

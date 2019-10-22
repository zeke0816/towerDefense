package game.characters.warriors;

import game.characters.Warrior;

public class BB8 extends Warrior {
	
	public BB8() {
		scope = 1;
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private BB8(BB8 target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new BB8(this);
	}
	
}

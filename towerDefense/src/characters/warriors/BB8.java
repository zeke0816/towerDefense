package characters.warriors;

import characters.Warrior;

public class BB8 extends Warrior {
	
	public BB8() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	public BB8(BB8 target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new BB8(this);
	}
	
}

package characters.warriors;

import characters.Warrior;

public class Gary extends Warrior {
	
	public Gary() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	public Gary(Gary target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Gary(this);
	}

}

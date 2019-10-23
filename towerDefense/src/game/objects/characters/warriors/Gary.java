package game.objects.characters.warriors;

import game.objects.characters.Warrior;

public class Gary extends Warrior {
	
	public Gary() {
		scope = 1;
		life = 2500;
		price = 90;
		strength = 400;
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

package characters.warriors;

import characters.Warrior;

public class Cyborg extends Warrior {
	
	public Cyborg() {
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

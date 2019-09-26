package characters.warriors;

import characters.Warrior;

public class Toph extends Warrior {
	
	public Toph() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private Toph(Toph target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Toph(this);
	}

}

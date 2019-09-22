package characters.warriors;

import characters.Warrior;

public class Toph extends Warrior {
	
	public Toph() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	public Toph(Toph target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Toph(this);
	}

}

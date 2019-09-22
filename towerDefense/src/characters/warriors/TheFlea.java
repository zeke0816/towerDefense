package characters.warriors;

import characters.Warrior;

public class TheFlea extends Warrior {
	
	public TheFlea() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	public TheFlea(TheFlea target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new TheFlea(this);
	}

}

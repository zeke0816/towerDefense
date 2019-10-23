package game.objects.characters.warriors;

import game.objects.characters.Warrior;

public class TheFlea extends Warrior {
	
	public TheFlea() {
		scope = 2;
		life = 500;
		price = 70;
		strength = 300;
		attackSpeed = 400;
	}
	
	private TheFlea(TheFlea target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new TheFlea(this);
	}

}

package game.objects.characters.warriors;

public class TheFlea extends Warrior {
	
	public TheFlea() {
		LIFE = 500;
		scope = 2;
		life = LIFE;
		price = 70;
		STRENGTH = 300;
		attackSpeed = 400;
		strength = STRENGTH;
	}
	
	private TheFlea(TheFlea target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new TheFlea(this);
	}

}

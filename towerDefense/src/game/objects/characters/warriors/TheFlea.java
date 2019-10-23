package game.objects.characters.warriors;

public class TheFlea extends Warrior {
	
	public TheFlea() {
		DEF_LIFE = 500;
		scope = 2;
		life = DEF_LIFE;
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

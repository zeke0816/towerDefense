package game.objects.characters.warriors;

public class TheFlea extends Warrior {
	
	public TheFlea() {
		id = "theFlea";
		name = "The Flea";
		LIFE = 1800;
		scope = 128;
		life = LIFE;
		price = 1000;
		STRENGTH = 1200;
		attackFrequency = 4000;
		strength = STRENGTH;
		playsSound = true;
	}
	
	private TheFlea(TheFlea target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new TheFlea(this);
	}

}

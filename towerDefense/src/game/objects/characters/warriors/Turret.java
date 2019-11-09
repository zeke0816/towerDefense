package game.objects.characters.warriors;

public class Turret extends Warrior {
	
	public Turret() {
		id = "turret";
		name = "Turret";
		LIFE = 1200;
		scope = 512;
		life = LIFE;
		price = 1300;
		STRENGTH = 1800;
		attackFrequency = 700;
		strength = STRENGTH;
		takesTwoCells = true;
	}
	
	private Turret(Turret target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Turret(this);
	}

}

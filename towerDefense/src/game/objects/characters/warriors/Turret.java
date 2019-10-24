package game.objects.characters.warriors;

public class Turret extends Warrior {
	
	public Turret() {
		LIFE = 1200;
		scope = 8;
		life = LIFE;
		price = 1300;
		STRENGTH = 500;
		attackSpeed = 400;
		strength = STRENGTH;
	}
	
	private Turret(Turret target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Turret(this);
	}

}

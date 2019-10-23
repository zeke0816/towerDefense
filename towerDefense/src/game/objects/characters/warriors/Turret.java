package game.objects.characters.warriors;

public class Turret extends Warrior {
	
	public Turret() {
		DEF_LIFE = 1200;
		scope = 8;
		life = DEF_LIFE;
		price = 1300;
		strength = 500;
		attackSpeed = 400;
	}
	
	private Turret(Turret target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Turret(this);
	}

}

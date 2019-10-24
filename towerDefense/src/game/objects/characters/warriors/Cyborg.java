package game.objects.characters.warriors;

public class Cyborg extends Warrior {
	
	public Cyborg() {
		LIFE= 2500;
		scope = 7;
		life = LIFE;
		price = 5000;
		STRENGTH = 1000;
		attackSpeed = 400;
		strength = STRENGTH;
	}
	
	private Cyborg(Cyborg target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Cyborg(this);
	}

}

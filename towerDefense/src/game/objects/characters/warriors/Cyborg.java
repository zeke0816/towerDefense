package game.objects.characters.warriors;

public class Cyborg extends Warrior {
	
	public Cyborg() {
		DEF_LIFE= 2500;
		scope = 7;
		life = DEF_LIFE;
		price = 5000;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private Cyborg(Cyborg target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Cyborg(this);
	}

}

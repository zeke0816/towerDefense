package game.objects.characters.warriors;

public class Cyborg extends Warrior {
	
	public Cyborg() {
		scope = 7;
		life = 2500;
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

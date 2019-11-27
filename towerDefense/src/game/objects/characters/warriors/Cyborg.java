package game.objects.characters.warriors;

public class Cyborg extends Warrior {
	
	public Cyborg() {
		id = "cyborg";
		name = "Cyborg";
		LIFE= 8000;
		scope = 640;
		life = LIFE;
		price = 10000;
		STRENGTH = 2000;
		attackFrequency = 600;
		strength = STRENGTH;
		playsSound = true;
	}
	
	private Cyborg(Cyborg target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Cyborg(this);
	}

}

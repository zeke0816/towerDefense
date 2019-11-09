package game.objects.characters.warriors;

public class Gary extends Warrior {
	
	public Gary() {
		id = "gary";
		name = "Gary";
		LIFE = 2500;
		scope = 64;
		life = LIFE;
		price = 90;
		STRENGTH = 400;
		attackFrequency = 5000;
		strength = STRENGTH;
		playsSound = true;
	}
	
	private Gary(Gary target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Gary(this);
	}

}

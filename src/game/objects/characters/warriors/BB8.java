package game.objects.characters.warriors;

public class BB8 extends Warrior {
	
	public BB8() {
		id = "bb8";
		name = "BB8";
		LIFE = 1700;
		scope = 64;
		life = LIFE;
		price = 750;
		STRENGTH = 600;
		attackFrequency = 1400;
		strength = STRENGTH;
	}
	
	private BB8(BB8 target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new BB8(this);
	}
	
}

package game.objects.characters.warriors;

public class BB8 extends Warrior {
	
	public BB8() {
		LIFE = 1700;
		scope = 1;
		life = LIFE;
		price = 30;
		STRENGTH = 600;
		attackSpeed = 400;
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

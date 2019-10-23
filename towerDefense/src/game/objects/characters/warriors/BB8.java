package game.objects.characters.warriors;

public class BB8 extends Warrior {
	
	public BB8() {
		DEF_LIFE = 700;
		scope = 1;
		life = DEF_LIFE;
		price = 30;
		strength = 200;
		attackSpeed = 400;
	}
	
	private BB8(BB8 target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new BB8(this);
	}
	
}

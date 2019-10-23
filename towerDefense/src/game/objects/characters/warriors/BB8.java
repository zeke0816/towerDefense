package game.objects.characters.warriors;

public class BB8 extends Warrior {
	
	public BB8() {
		scope = 1;
		life = 700;
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

package game.objects.characters.warriors;

public class Gary extends Warrior {
	
	public Gary() {
		LIFE = 2500;
		scope = 1;
		life = LIFE;
		price = 90;
		STRENGTH = 400;
		attackSpeed = 400;
		strength = STRENGTH;
	}
	
	private Gary(Gary target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Gary(this);
	}

}

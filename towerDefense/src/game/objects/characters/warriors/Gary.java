package game.objects.characters.warriors;

public class Gary extends Warrior {
	
	public Gary() {
		DEF_LIFE = 2500;
		scope = 1;
		life = DEF_LIFE;
		price = 90;
		strength = 400;
		attackSpeed = 400;
	}
	
	private Gary(Gary target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new Gary(this);
	}

}

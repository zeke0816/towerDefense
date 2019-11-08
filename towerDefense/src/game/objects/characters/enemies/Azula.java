package game.objects.characters.enemies;

public class Azula extends Enemy {
	public Azula() {
		LIFE = 1000;
		scope = 7;
		life = LIFE;
		points = 1000;
		STRENGTH = 1200;
		movementSpeed = 15;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Azula(Azula target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Azula(this);
	}
}

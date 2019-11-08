package game.objects.characters.enemies;

public class Jiren extends Enemy {

	public Jiren() {
		LIFE = 2000;
		scope = 10;
		life = LIFE;
		points = 1000;
		STRENGTH = 1000;
		movementSpeed = 5;
		life = LIFE;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Jiren(Jiren target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Jiren(this);
	}

}

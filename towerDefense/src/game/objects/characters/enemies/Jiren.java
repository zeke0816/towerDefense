package game.objects.characters.enemies;

public class Jiren extends Enemy {

	public Jiren() {
		id = "jiren";
		name = "Jiren";
		LIFE = 7000;
		scope = 640;
		life = LIFE;
		points = 1000;
		STRENGTH = 2400;
		movementFrequency = 1;
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

package game.objects.characters.enemies;

public class Doof extends Enemy {
	public Doof() {
		LIFE = 1000;
		scope = 2;
		life = LIFE;
		points = 2000;
		STRENGTH = 200;
		movementSpeed = 6;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Doof(Doof target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(this);
	}
}

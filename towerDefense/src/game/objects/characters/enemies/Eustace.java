package game.objects.characters.enemies;

public class Eustace extends Enemy {
	
	public Eustace() {
		LIFE = 1000;
		scope = 1;
		life = LIFE;
		points = 1600;
		STRENGTH = 100;
		movementSpeed = 6;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Eustace(Eustace target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Eustace(this);
	}
}

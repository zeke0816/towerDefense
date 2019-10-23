package game.objects.characters.enemies;

public class Eustace extends Enemy {
	
	public Eustace() {
		scope = 1;
		life = 1000;
		points = 1600;
		strength = 100;
		movementSpeed = 1;
	}
	
	private Eustace(Eustace target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Eustace(this);
	}
}

package game.objects.characters.enemies;

public class Doof extends Enemy {
	public Doof() {
		DEF_LIFE = 1000;
		scope = 2;
		life = DEF_LIFE;
		points = 2000;
		strength = 200;
		movementSpeed = 6;
	}
	
	private Doof(Doof target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Doof(this);
	}
}

package game.objects.characters.enemies;

public class Eustace extends Enemy {
	
	public Eustace() {
		DEF_LIFE = 1000;
		scope = 1;
		life = DEF_LIFE;
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

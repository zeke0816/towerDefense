package game.objects.characters.enemies;

public class Azula extends Enemy {
	public Azula() {
		DEF_LIFE = 1000;
		scope = 7;
		life = DEF_LIFE;
		points = 1000;
		strength = 1200;
		movementSpeed = 15;
	}
	
	private Azula(Azula target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Azula(this);
	}
}

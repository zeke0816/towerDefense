package game.objects.characters.enemies;

public class Alien extends Enemy {
	
	public Alien() {
		DEF_LIFE = 1000;
		scope = 3;
		life = DEF_LIFE;
		points = 300;
		strength = 800;
		movementSpeed = 4;
	}
	
	private Alien(Alien target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Alien(this);
	}
}

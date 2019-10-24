package game.objects.characters.enemies;

public class Alien extends Enemy {
	
	public Alien() {
		LIFE = 1000;
		scope = 3;
		life = LIFE;
		points = 300;
		STRENGTH = 800;
		movementSpeed = 10;
		life = LIFE;
		strength = STRENGTH;
	}
	
	private Alien(Alien target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Alien(this);
	}
}

package game.objects.characters.enemies;

public class Alien extends Enemy {
	
	public Alien() {
		scope = 3;
		life = 1000;
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

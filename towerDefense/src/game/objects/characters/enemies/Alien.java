package game.objects.characters.enemies;

public class Alien extends Enemy {
	
	public Alien() {
		id = "alien";
		name = "Alien";
		LIFE = 3000;
		scope = 192;
		life = LIFE;
		points = 300;
		STRENGTH = 800;
		movementFrequency = 10;
		life = LIFE;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Alien(Alien target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Alien(this);
	}
}

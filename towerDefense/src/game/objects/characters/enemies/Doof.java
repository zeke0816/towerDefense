package game.objects.characters.enemies;

public class Doof extends Enemy {
	
	public Doof() {
		id = "doof";
		name = "Dr. Doofenshmirtz";
		LIFE = 1000;
		scope = 128;
		life = LIFE;
		points = 2000;
		STRENGTH = 200;
		movementFrequency = 20;
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

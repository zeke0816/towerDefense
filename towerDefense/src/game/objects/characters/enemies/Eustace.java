package game.objects.characters.enemies;

public class Eustace extends Enemy {
	
	public Eustace() {
		id = "eustace";
		name = "Eustace";
		LIFE = 2000;
		scope = 64;
		life = LIFE;
		points = 1600;
		STRENGTH = 1000;
		movementFrequency = 25;
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

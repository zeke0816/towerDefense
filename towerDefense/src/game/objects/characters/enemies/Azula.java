package game.objects.characters.enemies;

public class Azula extends Enemy {
	
	public Azula() {
		id = "azula";
		name = "Azula";
		LIFE = 10000;
		scope = 448;
		life = LIFE;
		points = 1000;
		STRENGTH = 2500;
		movementFrequency = 5;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Azula(Azula target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Azula(this);
	}
}

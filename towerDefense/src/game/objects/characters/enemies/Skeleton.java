package game.objects.characters.enemies;

public class Skeleton extends Enemy {
	
	public Skeleton() {
		id = "skeleton";
		name = "Skeleton";
		LIFE = 2500;
		scope = 384;
		life = LIFE;
		points = 700;
		STRENGTH = 700;
		movementFrequency = 15;
		strength = STRENGTH;
		price = STRENGTH;
	}
	
	private Skeleton(Skeleton target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Skeleton(this);
	}
}

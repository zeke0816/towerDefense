package game.objects.characters.enemies;

public class Skeleton extends Enemy{
	public Skeleton() {
		LIFE = 1000;
		scope = 6;
		life = LIFE;
		points = 700;
		STRENGTH = 700;
		movementSpeed = 8;
		strength = STRENGTH;
	}
	
	private Skeleton(Skeleton target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Skeleton(this);
	}
}

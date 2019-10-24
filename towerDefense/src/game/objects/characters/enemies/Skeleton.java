package game.objects.characters.enemies;

public class Skeleton extends Enemy{
	public Skeleton() {
		DEF_LIFE = 1000;
		scope = 6;
		life = DEF_LIFE;
		points = 700;
		strength = 700;
		movementSpeed = 8;
	}
	
	private Skeleton(Skeleton target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Skeleton(this);
	}
}

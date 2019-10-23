package game.objects.characters.enemies;

import game.objects.characters.Enemy;

public class Skeleton extends Enemy{
	public Skeleton() {
		scope = 6;
		life = 1000;
		points = 700;
		strength = 700;
		movementSpeed = 1;
	}
	
	private Skeleton(Skeleton target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Skeleton(this);
	}
}

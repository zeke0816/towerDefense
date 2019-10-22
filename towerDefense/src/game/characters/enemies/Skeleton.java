package game.characters.enemies;

import game.characters.Enemy;

public class Skeleton extends Enemy{
	public Skeleton() {
		points = 700;
		movementSpeed = 1;
		scope = 6;
		life = 1000;
	}
	
	private Skeleton(Skeleton target) {
		super(target);
	}
	
	@Override
	public Enemy clone() {
		return new Skeleton(this);
	}
}

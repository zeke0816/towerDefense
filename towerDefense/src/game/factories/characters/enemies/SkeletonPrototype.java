package game.factories.characters.enemies;

import game.objects.characters.enemies.Skeleton;

public class SkeletonPrototype extends EnemyPrototype {
	public SkeletonPrototype() {

		id = "skeleton";
		name = "Skeleton";
		playsSound = false;
		
		enemy = new Skeleton();
	}
}

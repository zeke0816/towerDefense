package gui.factories.enemies;

import characters.enemies.Skeleton;

public class SkeletonPrototype extends EnemyPrototype {
	public SkeletonPrototype() {

		id = "skeleton";
		name = "Skeleton";
		playsSound = false;
		
		enemy = new Skeleton();
	}
}

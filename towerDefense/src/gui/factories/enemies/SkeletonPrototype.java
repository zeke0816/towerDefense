package gui.factories.enemies;

import characters.enemies.Skeleton;

public class SkeletonPrototype extends EnemyPrototype {
	public SkeletonPrototype() {

		id = "skeleton";
		name = "Skeleton";
		playsSound = true;
		
		enemy = new Skeleton();
	}
}

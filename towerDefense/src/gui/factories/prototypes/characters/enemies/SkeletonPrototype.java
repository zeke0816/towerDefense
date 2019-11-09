package gui.factories.prototypes.characters.enemies;

import game.objects.characters.enemies.Skeleton;
import gui.factories.prototypes.ObjectPrototype;

public class SkeletonPrototype extends ObjectPrototype {
	
	public SkeletonPrototype() {
		super();
		
		object = new Skeleton();
	}
	
}

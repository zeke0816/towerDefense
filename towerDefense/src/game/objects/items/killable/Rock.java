package game.objects.items.killable;

import game.objects.items.Item;

public class Rock extends KillableItem{
	
	public Rock() {
		scope = 0;
		life = 2500;
		price = 0;
		strength = 0;
		attackSpeed = 0;
	}
	
	public Rock(Rock target) {
		super(target);
	}
	
	@Override
	public Item clone() {
		return new Rock(this);
	}
}

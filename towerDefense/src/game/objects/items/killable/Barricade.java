package game.objects.items.killable;

import game.objects.GameObject;
import game.objects.items.Item;

public class Barricade extends KillableItem{

	public Barricade() {
		scope = 0;
		life = 6000;
		price = 2000;
		strength = 0;
		attackSpeed = 0;
	}
	
	public Barricade(Barricade target) {
		super(target);
	}
	
	@Override
	public Item clone() {
		return new Barricade(this);
	}
}

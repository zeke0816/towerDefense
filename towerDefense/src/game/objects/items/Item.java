package game.objects.items;

import game.GameObject;
import game.objects.characters.Enemy;
import game.objects.characters.Warrior;
import game.visitors.Visitor;

/**
 * Abstract Item Class
 */
public abstract class Item extends GameObject {

	protected Item() {
		points = 0;
		strength = 300;
		movementSpeed = 0;
	}
	
	protected Item(Item target) {
		super(target);
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

	public boolean attack(Warrior w) {
		return false;
	}
	
	public boolean attack(Enemy e) {
		int harm = e.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}
	
	public boolean attack(Item i) {
		int harm = i.getStrength() - protection;
		if(harm < 0) {
			harm = 0;
		}
		life -= harm;
		return true;
	}
	
}

package Item;

import game.GameObject;
import game.visitors.Visitor;

/**
 * Abstract Item Class
 */
public abstract class Item extends GameObject {

	protected Item() {
		
	} 
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}

package Item;

import game.GameObject;
import game.Visitor;

/**
 * Item abstact Class
*/
public abstract class Item extends GameObject {

	/**
	 * Atributo de prueba
	 */
	protected String name;
	
	protected Item() {
		
	} 
	
	/**
	 * Accept method, Inherited from GameObject
	 * Sends to the visitor, the "Item" object
	 */
	public void accept(Visitor v) {
		v.visitItem(this);
	}
}

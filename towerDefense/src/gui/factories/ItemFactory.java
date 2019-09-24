package gui.factories;

import gui.factories.items.AcquirableItemPrototype;
import gui.factories.items.PreciousItemPrototype;
import gui.factories.items.RandomItemPrototype;

/**
 * TODO: agregar una jerarquia de herencia mas. Transformar 
 * las clases de tipo de Item, en abstactas, y agregar 
 * la clase concreta de cada item.
 * 
 * 
 * 
 * Class that handles the creation of items of the game
 * @author dimax
 *
 */
public class ItemFactory {
	
	protected AcquirableItemPrototype acquirable;
	protected PreciousItemPrototype precious;
	protected RandomItemPrototype random;
	
	private static final ItemFactory instance = new ItemFactory();
	

	public ItemFactory() {
		// TODO Auto-generated constructor stub
	}

}

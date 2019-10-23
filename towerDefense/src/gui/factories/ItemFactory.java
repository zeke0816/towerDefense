package gui.factories;

import gui.factories.items.KillableItemPrototype;
import gui.factories.items.TemporaryCharmPrototype;
import gui.factories.items.PermanentCharmPrototype;

/**
 * Class that handles the creation of Items of the game
 * @author dimax
 *
 */
public class ItemFactory {
	
	protected KillableItemPrototype acquirable;
	protected TemporaryCharmPrototype precious;
	protected PermanentCharmPrototype random;
	
	private static final ItemFactory instance = new ItemFactory();
	
	private ItemFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static ItemFactory getInstance() {
		return instance;
	}

}

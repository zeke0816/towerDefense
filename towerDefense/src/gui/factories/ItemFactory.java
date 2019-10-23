package gui.factories;

import gui.factories.items.RockPrototype;
import gui.factories.items.ShieldPrototype;

import java.util.ArrayList;
import java.util.Random;

import gui.factories.items.CurePrototype;
import gui.factories.items.ItemPrototype;
import gui.factories.items.NukePrototype;
import gui.factories.items.TankPrototype;
import gui.factories.items.PoisonPrototype;
import gui.factories.items.BarricadePrototype;

/**
 * Class that handles the creation of Items of the game
 * @author dimax
 */
public class ItemFactory {
	
	protected ArrayList<ItemPrototype> randomItems;
	protected ArrayList<ItemPrototype> droppableItems;
	
	private static final ItemFactory instance = new ItemFactory();
	
	/**
	 * Initializes the Item factory with a list for random items and a list for droppable items
	 */
	private ItemFactory() {
		randomItems = new ArrayList<ItemPrototype>();
		droppableItems = new ArrayList<ItemPrototype>();
		
		randomItems.add(new RockPrototype());
		randomItems.add(new TankPrototype());
		
		droppableItems.add(new PoisonPrototype());
		droppableItems.add(new ShieldPrototype());
		droppableItems.add(new CurePrototype());
		droppableItems.add(new BarricadePrototype());
		droppableItems.add(new NukePrototype());
	}
	
	/**
	 * Gets the instance of this class
	 * @return the instance
	 */
	public static ItemFactory getInstance() {
		return instance;
	}

	/**
	 * Returns a randomly chosen Random Item
	 * @return a Random item
	 */
	public ItemPrototype createRandomItem() {
		Random ran = new Random();
		int randomInt = ran.nextInt(2);
		return randomItems.get(randomInt);
	}
	
	/**
	 * Returns a randomly chosen Droppable Item
	 * @return a droppable item
	 */
	public ItemPrototype createDroppableItem() {
		Random ran = new Random();
		int randomInt = ran.nextInt(6);
		return droppableItems.get(randomInt);
	}

}

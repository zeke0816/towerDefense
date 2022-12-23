package gui.factories;

import java.util.ArrayList;
import java.util.Random;

import gui.factories.prototypes.ObjectPrototype;
import gui.factories.prototypes.items.BarricadePrototype;
import gui.factories.prototypes.items.CurePrototype;
import gui.factories.prototypes.items.NukePrototype;
import gui.factories.prototypes.items.PoisonPrototype;
import gui.factories.prototypes.items.RockPrototype;
import gui.factories.prototypes.items.ShieldPrototype;
import gui.factories.prototypes.items.TankPrototype;

/**
 * Class that handles the creation of Items of the game
 * @author dimax
 */
public class ItemFactory {
	
	protected ArrayList<ObjectPrototype> randomItems;
	protected ArrayList<ObjectPrototype> droppableItems;
	
	private static final ItemFactory instance = new ItemFactory();
	
	/**
	 * Initializes the Item factory with a list for random items and a list for droppable items
	 */
	private ItemFactory() {
		randomItems = new ArrayList<ObjectPrototype>();
		droppableItems = new ArrayList<ObjectPrototype>();
		
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
	public ObjectPrototype createRandomItem() {
		Random ran = new Random();
		int randomInt = ran.nextInt(2);
		return randomItems.get(randomInt);
	}
	
	/**
	 * Returns a randomly chosen Droppable Item
	 * @return a droppable item
	 */
	public ObjectPrototype createDroppableItem() {
		Random ran = new Random();
		int randomInt = ran.nextInt(5);
		return droppableItems.get(randomInt);
	}

}

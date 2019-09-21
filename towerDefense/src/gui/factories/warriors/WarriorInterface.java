package gui.factories.warriors;

import characters.Warrior;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Class to represent and save information about a Warrior in the GUI
 * @author zeke0816
 *
 */
public abstract class WarriorInterface {
	
	protected String id;
	protected String name;
	protected boolean playsSound;
	protected Label label;
	protected Button button;
	protected Warrior warrior;
	
	/**
	 * Creates an empty Warrior Interface
	 */
	protected WarriorInterface() {
		
	}
	
	/**
	 * Clones and gets the Warrior to be 
	 * @return the Warrior
	 */
	public Warrior getWarrior() {
		return warrior;
	}
	
	/**
	 * Gets the ID string for media identification
	 * @return the ID of the warrior interface
	 */
	public abstract String getID();
	
	/**
	 * Gets the display name of the warrior
	 * @return the display name
	 */
	public abstract String getName();
	
	/**
	 * Tells whether this warrior plays sounds or not
	 * @return true if it plays sounds, false if it does not
	 */
	public abstract boolean playsSound();
	
	/**
	 * Gets the label to show on screen
	 * @return the label
	 */
	public abstract Label getLabel();
	
	/**
	 * Gets the button to show on screen
	 * @return the button
	 */
	public abstract Button getButton();

}

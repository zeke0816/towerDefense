package gui.attacks;

import game.objects.GameObject;
import javafx.scene.control.Label;

public abstract class Attack extends Label {
	
	/**
	 * Initializes the Attack as a simple Label
	 */
	protected Attack() {
		super();
	}
	
	/**
	 * Perform the blast, blasting the given Game Object in the process
	 * @param o the Game Object
	 */
	public abstract void shoot(GameObject o);
	
}

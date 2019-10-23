package gui.controls;

import game.factories.characters.warriors.WarriorPrototype;
import javafx.scene.control.Button;

public class WarriorButton extends Button {
	
	protected WarriorPrototype warrior;
	
	public WarriorButton() {
		super();
	}
	
	public void setWarrior(WarriorPrototype w) {
		warrior = w;
	}
	
	public WarriorPrototype getWarrior() {
		return warrior;
	}
	
}

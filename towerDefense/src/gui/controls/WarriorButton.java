package gui.controls;

import gui.factories.warriors.WarriorPrototype;
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

package gui.factories.warriors;

import characters.warriors.AgentP;
import exceptions.DatabaseException;
import gui.controls.WarriorButton;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import media.databases.MediaDatabase;

public class AgentPPrototype extends WarriorPrototype {

	public AgentPPrototype() {
		super();
		
		id = "agentP";
		name = "Agent P";
		playsSound = true;
		
		label = new Label(name);
		label.setVisible(true);
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font("Cambria", 20));
        GridPane.setHalignment(label, HPos.CENTER);
		
		double size = 100;
		button = new WarriorButton();
		button.setVisible(true);
        button.setPrefSize(size, size);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new AgentP();
	}
	
	public String getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean playsSound() {
		return playsSound;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public WarriorButton getButton() {
		return button;
	}

}

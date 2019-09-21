package gui.factories.warriors;

import characters.Warrior;
import characters.warriors.AgentP;
import exceptions.DatabaseException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import media.databases.MediaDatabase;

public class AgentPInterface extends WarriorInterface {

	public AgentPInterface() {
		super();
		
		id = "agentP";
		name = "Agent P";
		playsSound = true;
		
		label = new Label(name);
		label.setVisible(true);
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font("Cambria", 20));
		
		double size = 100;
		button = new Button();
		button.setVisible(true);
        button.setPrefSize(size, size);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setUserData(this);
        warrior = new AgentP();
	}
	
	public Warrior getWarrior() {
		return warrior;
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
	
	public Button getButton() {
		return button;
	}

}

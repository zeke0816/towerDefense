package gui.factories.warriors;

import exceptions.DatabaseException;
import game.objects.characters.warriors.AgentP;
import media.databases.MediaDatabase;

public class AgentPPrototype extends WarriorPrototype {

	public AgentPPrototype() {
		super();
		
		id = "agentP";
		name = "Agent P";
		playsSound = true;

		nameLabel.setText(name);
        try {
			button.setBackground(MediaDatabase.getInstance().getImageBackgroundMedia(id, size, size, true, false));
		} catch (DatabaseException e) {
			System.out.println("The Warrior's graphics could not be loaded.");
		}
        button.setWarrior(this);
        warrior = new AgentP();
        priceLabel.setText("$ "+warrior.getPrice());
	}

}

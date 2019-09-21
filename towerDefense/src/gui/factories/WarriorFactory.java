package gui.factories;

import characters.Warrior;
import characters.warriors.BB8;
import characters.warriors.Cyborg;
import characters.warriors.Gary;
import characters.warriors.TheFlea;
import characters.warriors.Toph;
import characters.warriors.Turret;
import gui.factories.warriors.AgentPInterface;
import gui.factories.warriors.WarriorInterface;

/**
 * Class that handles the creation of characters and objects
 * @author zeke0816
 *
 */
public class WarriorFactory {
	
	protected WarriorInterface agentP;
	private static final WarriorFactory instance = new WarriorFactory();
	
	private WarriorFactory() {
		agentP = new AgentPInterface();
	}
	
	public static WarriorFactory getInstance() {
		return instance;
	}
	
	/**
	 * Creates a Warrior based on the given ID
	 * @param id the ID of the Warrior
	 * @return the newly created Warrior
	 */
	public Warrior createWarrior(String id) {
		switch(id) {
			case "agentP":
				return agentP.getWarrior();
			case "theFlea":
				return new TheFlea();
			case "cyborg":
				return new Cyborg();
			case "bb8":
				return new BB8();
			case "gary":
				return new Gary();
			case "turret":
				return new Turret();
			case "toph":
				return new Toph();
		}
		return null;
	}

}

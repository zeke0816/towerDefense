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
	public Warrior createWarrior(int id) {
		switch(id) {
			case 1:
				return agentP.getWarrior();
			case 2:
				return new TheFlea();
			case 3:
				return new Cyborg();
			case 4:
				return new BB8();
			case 5:
				return new Gary();
			case 6:
				return new Turret();
			case 7:
				return new Toph();
		}
		return null;
	}

}

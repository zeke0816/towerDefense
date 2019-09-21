package game;

import characters.Warrior;
import characters.warriors.AgentP;
import characters.warriors.BB8;
import characters.warriors.Cyborg;
import characters.warriors.Gary;
import characters.warriors.TheFlea;
import characters.warriors.Toph;
import characters.warriors.Turret;

/**
 * Class that handles the creation of characters and objects
 * @author zeke0816
 *
 */
public class WarriorFactory {
	
	/**
	 * Creates a Warrior based on the given ID
	 * @param id the ID of the Warrior
	 * @return the newly created Warrior
	 */
	public Warrior createWarrior(String id) {
		switch(id) {
			case "AgentP":
				return new AgentP();
			case "TheFlea":
				return new TheFlea();
			case "Cyborg":
				return new Cyborg();
			case "BB8":
				return new BB8();
			case "Gary":
				return new Gary();
			case "Turret":
				return new Turret();
			case "Toph":
				return new Toph();
		}
		return null;
	}

}

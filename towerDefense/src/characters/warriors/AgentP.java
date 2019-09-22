package characters.warriors;

import characters.Warrior;

public class AgentP extends Warrior {
	
	public AgentP() {
		life = 2500;
		strength = 1000;
		attackSpeed = 400;
	}
	
	public AgentP(AgentP target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new AgentP(this);
	}

}

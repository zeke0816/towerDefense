package game.objects.characters.warriors;

public class AgentP extends Warrior {
	
	public AgentP() {
		id = "agentP";
		name = "Agent P";
		LIFE = 2500;
		life = LIFE;
		scope = 448;
		price = 7000;
		STRENGTH = 1000;
		attackFrequency = 800;
		strength = STRENGTH;
		playsSound = true;
	}
	
	private AgentP(AgentP target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new AgentP(this);
	}

}

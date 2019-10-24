package game.objects.characters.warriors;

public class AgentP extends Warrior {
	
	public AgentP() {
		LIFE = 2500;
		life = LIFE;
		scope = 5;
		price = 2300;
		STRENGTH = 1000;
		attackSpeed = 400;
		strength = STRENGTH;
	}
	
	private AgentP(AgentP target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new AgentP(this);
	}

}

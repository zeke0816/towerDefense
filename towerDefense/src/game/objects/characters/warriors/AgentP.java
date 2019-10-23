package game.objects.characters.warriors;

public class AgentP extends Warrior {
	
	public AgentP() {
		scope = 5;
		life = 2500;
		price = 2300;
		strength = 1000;
		attackSpeed = 400;
	}
	
	private AgentP(AgentP target) {
        super(target);
	}
	
	@Override
	public Warrior clone() {
		return new AgentP(this);
	}

}

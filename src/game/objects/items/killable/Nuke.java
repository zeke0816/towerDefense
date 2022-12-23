package game.objects.items.killable;

public class Nuke extends KillableItem {

	public Nuke() {
		id = "nuke";
		name = "Nuke";
		scope = 192;
		LIFE = 1000000;
		price = 1000000;
		attackFrequency = 1500;
		STRENGTH = 1000000;
		protection = life;
		squaredScope = true;
		life = LIFE;
		strength = STRENGTH;
	} 
	
	private Nuke(Nuke target) {
		super(target);
	}

	@Override
	public Nuke clone() {
		return new Nuke(this);
	}
}

package game.objects.items.killable;

public class Nuke extends KillableItem {

	public Nuke() {
		id = "nuke";
		name = "Nuke";
		scope = 192;
		LIFE = 10000;
		price = 0;
		attackFrequency = 1500;
		STRENGTH = 10000;
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

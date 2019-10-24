package game.objects.items.killable;

public class Nuke extends KillableItem {

	public Nuke() {
		scope = 3;
		LIFE = 3;
		price = 0;
		attackSpeed = 1;
		STRENGTH = 7000;
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

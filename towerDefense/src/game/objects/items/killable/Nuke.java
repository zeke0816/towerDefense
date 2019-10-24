package game.objects.items.killable;

public class Nuke extends KillableItem {

	public Nuke() {
		scope = 3;
		life = 3;
		price = 0;
		attackSpeed = 1;
		strength = 7000;
		protection = life;
		squaredScope = true;
	} 
	
	private Nuke(Nuke target) {
		super(target);
	}

	@Override
	public Nuke clone() {
		return new Nuke(this);
	}
}

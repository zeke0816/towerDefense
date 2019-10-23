package game.objects.items.killable;

public class Nuke extends KillableItem {

	public Nuke() {
		scope = 3;	//TODO: NUKE ATTACK
		life = 3;
		price = 0;
		strength = 7000;
		attackSpeed = 1;
	} 
	
	private Nuke(Nuke target) {
		super(target);
	}

	@Override
	public Nuke clone() {
		return new Nuke(this);
	}
}

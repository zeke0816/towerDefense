package game.objects.items.killable;

public class Rock extends KillableItem{
	
	public Rock() {
		scope = 0;
		life = 2500;
		price = 0;
		strength = 0;
		attackSpeed = 0;
	}
	
	public Rock(Rock target) {
		super(target);
	}
	
	@Override
	public Rock clone() {
		return new Rock(this);
	}
}

package game.objects.items.killable;

public class Rock extends KillableItem{
	
	public Rock() {
		scope = 0;
		LIFE = 2500;
		price = 0;
		STRENGTH = 0;
		attackSpeed = 0;
		life = LIFE;
		strength = STRENGTH;
	}
	
	public Rock(Rock target) {
		super(target);
	}
	
	@Override
	public Rock clone() {
		return new Rock(this);
	}
}

package game.objects.items.killable;

public class Rock extends KillableItem{
	
	public Rock() {
		id = "rock";
		name = "Rock";
		scope = 0;
		LIFE = 2500;
		price = 0;
		STRENGTH = 0;
		attackFrequency = 90000;
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

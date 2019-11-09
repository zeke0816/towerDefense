package game.objects.items.killable;

public class Tank extends KillableItem {

	public Tank() {
		id = "tank";
		name = "Tank";
		scope = 320;
		LIFE = 1000;
		price = 0;
		STRENGTH = 250;
		attackFrequency = 4000;
		life = LIFE;
		strength = STRENGTH;
	}
	
	private Tank(Tank target) {
		super(target);
	}
	
	@Override
	public Tank clone() {
		return new Tank(this);
	}
	
}

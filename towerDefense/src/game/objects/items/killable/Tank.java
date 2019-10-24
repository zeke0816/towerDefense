package game.objects.items.killable;

public class Tank extends KillableItem{

	public Tank() {
		scope = 5;
		LIFE = 1000;
		price = 0;
		STRENGTH = 250;
		attackSpeed = 4;
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

package game.objects.items.killable;

public class Tank extends KillableItem{

	public Tank() {
		scope = 5;
		life = 1000;
		price = 0;
		strength = 250;
		attackSpeed = 4;
	}
	
	private Tank(Tank target) {
		super(target);
	}
	
	@Override
	public Tank clone() {
		return new Tank(this);
	}
	
}

package game.objects.items.killable;

public class Barricade extends KillableItem{

	public Barricade() {
		scope = 0;
		LIFE = 6000;
		price = 2000;
		STRENGTH = 0;
		attackSpeed = 0;
		life = LIFE;
		strength = STRENGTH;
	}
	
	public Barricade(Barricade target) {
		super(target);
	}
	
	@Override
	public Barricade clone() {
		return new Barricade(this);
	}
}

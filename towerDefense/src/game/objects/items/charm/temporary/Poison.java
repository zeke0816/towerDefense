package game.objects.items.charm.temporary;


public class Poison extends TemporaryCharm{

	private Poison(Poison target) {
        super(target);
	}
	
	@Override
	public TemporaryCharm clone() {
		return new Poison(this);
	}

}	

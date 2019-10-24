package game.objects.items.charm.temporary;

import game.objects.GameObject;
import game.objects.characters.states.Basic;
import game.objects.characters.states.Shielded;

public class Shield extends TemporaryCharm {

	public Shield(){
		price = 2500;
	}	
	
	protected Shield(Shield target) {
		super(target);
	}
	
	@Override
	public Shield clone() {
		return new Shield(this);
	}
	
	public boolean doAction(GameObject o) {
		o.changeState(new Shielded(o));
		object = o;
		return true;
	}
	
	public boolean undoAction() {
		object.changeState(new Basic(object));
		return true;
	}
	
	
}

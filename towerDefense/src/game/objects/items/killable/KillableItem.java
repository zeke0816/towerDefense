package game.objects.items.killable;

import exceptions.InvalidActionException;
import game.Game;
import game.objects.items.Item;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import visitors.Visitor;

/**
 * Killable Item class
 */
public abstract class KillableItem extends Item {
	
	protected KillableItem() {
		
	}
	
	protected KillableItem(KillableItem target) {
		super(target);
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public boolean attack(KillableItem k) {
		boolean attacked = false;
		attackAttempts++;
		if(attackAttempts == attackFrequency) {
			int harm = k.getStrength() - protection;
			if(harm < 0) {
				harm = 0;
			}
			life -= harm;
			if(isDead()) {
				try {
					Game.getInstance().getMap().freeCell(this);
				} catch (InvalidActionException e1) {
					System.out.println(e1.getMessage());
				}
			}
			attacked = true;
			resetAttackAttempts();
		}
		return attacked;
	}

	public boolean attack(TemporaryCharm t) {
		return false;
	}

	public boolean attack(PermanentCharm p) {
		return false;
	}
	
}

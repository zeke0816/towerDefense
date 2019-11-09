package game.objects.characters.warriors;

import exceptions.InvalidActionException;
import game.Game;
import game.objects.characters.Character;
import game.objects.characters.enemies.Enemy;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import visitors.Visitor;

/**
 * Abstract class that helps define the warriors in the game
 * @author zeke0816
 *
 */
public abstract class Warrior extends Character {
	
	protected Warrior() {
		movementFrequency = 0;
	}
	
	protected Warrior(Warrior target) {
		super(target);
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public boolean attack(Warrior w) {
		return false;
	}
	
	public boolean attack(Enemy e) {
		boolean attacked = false;
		attackAttempts++;
		if(attackAttempts == attackFrequency) {
			int harm = e.getStrength() - protection;
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

	/**
	 * Creates and returns a copy of this Warrior
	 */
	public abstract Warrior clone();

}

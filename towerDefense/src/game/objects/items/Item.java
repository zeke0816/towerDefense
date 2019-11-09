package game.objects.items;

import exceptions.InvalidActionException;
import game.Game;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;

/**
 * Abstract Item Class
 */
public abstract class Item extends GameObject {
	
	protected boolean squaredScope;

	protected Item() {
		squaredScope = false;
		points = 0;
		movementFrequency = 0;
	}
	
	protected Item(Item target) {
		super(target);
		if(target != null) {
			squaredScope = target.hasSquaredScope();
		}
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
	
	public boolean hasSquaredScope() {
		return squaredScope;
	}
	
}

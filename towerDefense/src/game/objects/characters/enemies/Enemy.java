package game.objects.characters.enemies;

import exceptions.InvalidActionException;
import game.Game;
import game.objects.characters.Character;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import visitors.Visitor;

/**
 * Abstract class that helps define the enemies in the game
 * @author zeke0816
 *
 */
public abstract class Enemy extends Character {
	
	protected int points;
	protected int worth;
	protected final int increment = 200;
	
	protected Enemy() {
		points = 0;
		worth = 3000;
		drops = true;
	}
	
	protected Enemy(Enemy target) {
		super(target);
		points = target.getPoints();
		worth = target.getWorth();
	}
	
	/**
	 * Gets the points given by this Enemy
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Gets the worth of this Enemy
	 * @return the worth
	 */
	public int getWorth() {
		return worth;
	}
	
	public void accept(Visitor v) {
		v.visit(this);
	}

	public boolean attack(Warrior w) {
		boolean attacked = false;
		attackAttempts++;
		if(attackAttempts == attackFrequency) {
			int harm = w.getStrength() - protection;
			if(harm < 0) {
				harm = 0;
			}
			life -= harm;
			if(isDead()) {
				try {
					Game game = Game.getInstance();
					game.increaseBudget(getWorth());
					game.getLevel().getWave().kill();
					game.getMap().freeCell(this);
					game.updateScore(points);
				} catch(InvalidActionException e) {
					System.out.println(e.getMessage());
				}
			}
			attacked = true;
			resetAttackAttempts();
		}
		return attacked;
	}
	
	public boolean attack(Enemy e) {
		return false;
	}
	
	public boolean attack(KillableItem i) {
		boolean attacked = false;
		attackAttempts++;
		if(attackAttempts == attackFrequency) {
			int harm = i.getStrength() - protection;
			if(harm < 0) {
				harm = 0;
			}
			life -= harm;
			if(isDead()) {
				try {
					Game game = Game.getInstance();
					game.increaseBudget(getWorth());
					game.getLevel().getWave().kill();
					game.getMap().freeCell(this);
					game.updateScore(points);
				} catch(InvalidActionException e) {
					System.out.println(e.getMessage());
				}
			}
			attacked = true;
			resetAttackAttempts();
		}
		return attacked;
	}
	
	public boolean attack(TemporaryCharm i) {
		return false;
	}
	
	public boolean attack(PermanentCharm i) {
		return false;
	}
	
	/**
	 * Creates and returns a copy of this Enemy.
	 */
	public abstract Enemy clone();
	
}
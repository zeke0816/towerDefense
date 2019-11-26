package game.objects.characters.enemies;

import java.util.ArrayList;

import exceptions.InvalidActionException;
import game.Cell;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.characters.Character;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import visitors.GameObjectVisitor;

/**
 * Abstract class that helps define the enemies in the game
 * @author zeke0816
 *
 */
public abstract class Enemy extends Character {
	
	protected int points;
	protected int worth;
	protected final int increment = 200;
	
	/**
	 * Initializes an enemy with 0 reward points, 3000 worth in money and allowing it to drop stuff when killed
	 */
	protected Enemy() {
		points = 0;
		worth = 3000;
		drops = true;
	}
	
	/**
	 * Initializes an Enemy by cloning another one
	 * @param target
	 */
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
	
	public void accept(GameObjectVisitor v) {
		v.visit(this);
	}

	public boolean attack(Warrior w) {
		boolean attacked = false;
		w.attemptAttack();
		if(w.getAttackAttempts() == w.getAttackFrequency()) {
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
			w.resetAttackAttempts();
		}
		return attacked;
	}
	
	public boolean attack(Enemy e) {
		return false;
	}
	
	public boolean attack(KillableItem i) {
		boolean attacked = false;
		i.attemptAttack();
		if(i.getAttackAttempts() == i.getAttackFrequency()) {
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
			i.resetAttackAttempts();
		}
		return attacked;
	}
	
	public boolean attack(TemporaryCharm i) {
		return false;
	}
	
	public boolean attack(PermanentCharm i) {
		return false;
	}
	
	public ArrayList<GameObject> fight() {
		Map map = Game.getInstance().getMap();
		ArrayList<GameObject> opponents = new ArrayList<GameObject>();
		boolean fought = false;
		if(strength > 0) {
			int laneLimit = lane;
			if(takesTwoCells) {
				laneLimit++;
			}
			for(int i = lane; i <= laneLimit; i++) {
				for(int j = 1; !fought && j <= scope && j <= distance; j++) {
					Cell cell = map.getCell(lane, distance - j);
					if(cell.isTaken()) {
						GameObject opponent = cell.getObject();
						fought = opponent.attack(this);
						if(fought) {
							opponents.add(opponent);
						}
					}
				}
			}
		}
		return opponents;
	}
	
	/**
	 * Creates and returns a copy of this Enemy.
	 */
	public abstract Enemy clone();
	
}
package game.objects.characters.warriors;

import java.util.ArrayList;

import exceptions.InvalidActionException;
import game.Cell;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.characters.Character;
import game.objects.characters.enemies.Enemy;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import visitors.GameObjectVisitor;

/**
 * Abstract class that helps define the warriors in the game
 * @author zeke0816
 *
 */
public abstract class Warrior extends Character {
	
	/**
	 * Initializes a Warrior with the highest movement frequency
	 */
	protected Warrior() {
		movementFrequency = 0;
	}
	
	/**
	 * Initializes a Warrior by cloning another one
	 * @param target
	 */
	protected Warrior(Warrior target) {
		super(target);
	}

	public void accept(GameObjectVisitor v) {
		v.visit(this);
	}

	public boolean attack(Warrior w) {
		return false;
	}
	
	public boolean attack(Enemy e) {
		boolean attacked = false;
		e.attemptAttack();
		if(e.getAttackAttempts() == e.getAttackFrequency()) {
			System.out.println("Attacked");
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
			e.resetAttackAttempts();
		}
		return attacked;
	}
	
	public boolean attack(KillableItem k) {
		boolean attacked = false;
		k.attemptAttack();
		if(k.getAttackAttempts() == k.getAttackFrequency()) {
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
			k.resetAttackAttempts();
		}
		return attacked;
	}
	
	public boolean attack(TemporaryCharm t) {
		return false;
	}
	
	public boolean attack(PermanentCharm p) {
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
				for(int j = 1; !fought && j <= scope && (j + distance) < map.getDistance(); j++) {
					Cell cell = map.getCell(i, distance + j);
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
	 * Creates and returns a copy of this Warrior
	 */
	public abstract Warrior clone();

}

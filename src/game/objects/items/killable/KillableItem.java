package game.objects.items.killable;

import java.util.ArrayList;

import exceptions.InvalidActionException;
import game.Cell;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.items.Item;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import visitors.GameObjectVisitor;

/**
 * Class that defines a Killable Item
 * @author zeke0816
 *
 */
public abstract class KillableItem extends Item {
	
	/**
	 * Intializes an empty Killable Item
	 */
	protected KillableItem() {
		
	}
	
	/**
	 * Initializes a Killable Item by cloning another one
	 * @param target
	 */
	protected KillableItem(KillableItem target) {
		super(target);
	}
	
	public void accept(GameObjectVisitor v) {
		v.visit(this);
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
			if(squaredScope) {
				int calcDistance = distance + (Map.cellSize / 2);
				int top = lane - scope;
				int right = calcDistance + scope;
				int bottom = lane + scope;
				int left = calcDistance - scope;
				
				if(top < 0) {
					top = 0;
				}
				if(left < 0) {
					left = 0;
				}
				if(right >= map.getDistance()) {
					right = map.getDistance()-1;
				}
				if(bottom >= map.getLanes()) {
					bottom = map.getLanes()-1;
				}
				for(int i = top; i <= bottom; i++) {
					for(int j = left; j <= right; j++) {
						Cell cell = map.getCell(i, j);
						if(cell.isTaken()) {
							GameObject opponent = cell.getObject();
							fought = opponent.attack(this);
							if(fought) {
								opponents.add(opponent);
							}
						}
					}
				}
			} else {
				int laneLimit = lane;
				if(takesTwoCells) {
					laneLimit++;
				}
				for(int i = lane; i <= laneLimit; i++) {
					for(int j = 1; !fought && j <= scope && (j + distance) < map.getDistance(); j++) {
						Cell cell = map.getCell(lane, distance + j);
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
		}
		return opponents;
	}
	
}

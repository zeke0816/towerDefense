package visitors;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Cell;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;

/**
 * Class that handles the visits to each game object for moving and attacking
 * @author zeke0816
 *
 */
public class BattleVisitor implements Visitor {
	
	protected Map map;
	
	/**
	 * Initializes the visitor with a map reference to use in the methods
	 */
	public BattleVisitor() {
		map = Game.getInstance().getMap();
	}

	@Override
	public void visit(Warrior w) {
		if(w.getStrength() > 0) {
			boolean attacked = false;
			int lane = w.getLane();
			int distance = w.getDistance();
			int laneLimit = lane;
			if(w.takesTwoCells()) {
				laneLimit++;
			}
			for(int i = lane; i <= laneLimit; i++) {
				for(int j = 1; !attacked && j <= w.getScope() && (j + distance) < map.getDistance(); j++) {
					Cell cell = map.getCell(i, distance + j);
					if(cell.isTaken()) {
						GameObject opponent = cell.getObject();
						attacked = opponent.attack(w);
						if(attacked) {
							MovementLayout.getInstance().attackObject(w, opponent);
						}
					}
				}
			}
		}
	}

	@Override
	public void visit(Enemy e) {
		boolean attacked = false;
		int lane = e.getLane();
		int distance = e.getDistance();
		try {
			MovementLayout.getInstance().moveObject(e);
		} catch (InvalidActionException | CellTakenException e1) {
			System.out.println(e1.getMessage());
		}
		if(e.getStrength() > 0) {
			int laneLimit = lane;
			if(e.takesTwoCells()) {
				laneLimit++;
			}
			for(int i = lane; i <= laneLimit; i++) {
				for(int j = 1; !attacked && j <= e.getScope() && j <= distance; j++) {
					Cell cell = map.getCell(lane, distance - j);
					if(cell.isTaken()) {
						GameObject opponent = cell.getObject();
						attacked = opponent.attack(e);
						if(attacked) {
							MovementLayout.getInstance().attackObject(e, opponent);
						}
					}
				}
			}
		}
	}

	@Override
	public void visit(KillableItem it) {
		if(it.getStrength() > 0) {
			boolean attacked = false;
			int lane = it.getLane();
			int distance = it.getDistance() + (Map.cellSize / 2);
			int scope = it.getScope();
			if(it.hasSquaredScope()) {
				int top = lane - scope;
				int right = distance + scope;
				int bottom = lane + scope;
				int left = distance - scope;
				
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
							attacked = opponent.attack(it);
							if(attacked && opponent.isDead()) {
								MovementLayout.getInstance().removeObject(opponent);
							}
						}
					}
				}
				MovementLayout.getInstance().explodeRegion(it, left, top, scope * 2);
				PlacementLayout.getInstance().killObject(it);
				StatusLayout.getInstance().updateLevel();
			} else {
				int laneLimit = lane;
				if(it.takesTwoCells()) {
					laneLimit++;
				}
				for(int i = lane; i <= laneLimit; i++) {
					for(int j = 1; !attacked && j <= scope && (j + distance) < map.getDistance(); j++) {
						Cell cell = map.getCell(lane, distance + j);
						if(cell.isTaken()) {
							GameObject opponent = cell.getObject();
							attacked = opponent.attack(it);
							if(attacked) {
								MovementLayout.getInstance().attackObject(it, opponent);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void visit(PermanentCharm p) {
		// Never going to happen, they are not on the arena
	}

	@Override
	public void visit(TemporaryCharm t) {
		// Never going to happen, they are not on the arena
	}

}

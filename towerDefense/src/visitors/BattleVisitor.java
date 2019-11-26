package visitors;

import java.util.ArrayList;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
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
public class BattleVisitor implements GameObjectVisitor {
	
	/**
	 * Initializes the visitor with a map reference to use in the methods
	 */
	public BattleVisitor() {
		
	}

	@Override
	public void visit(Warrior w) {
		ArrayList<GameObject> opponents = w.fight();
		if(!opponents.isEmpty()) {
			for(int i = 0; i < opponents.size(); i++) {
				MovementLayout.getInstance().attackObject(w, opponents.get(i));
			}
		}
	}

	@Override
	public void visit(Enemy e) {
		try {
			MovementLayout.getInstance().moveObject(e);
		} catch (InvalidActionException | CellTakenException e1) {
			System.out.println(e1.getMessage());
		}
		ArrayList<GameObject> opponents = e.fight();
		if(!opponents.isEmpty()) {
			for(int i = 0; i < opponents.size(); i++) {
				MovementLayout.getInstance().attackObject(e, opponents.get(i));
			}
		}
	}

	@Override
	public void visit(KillableItem it) {
		ArrayList<GameObject> opponents = it.fight();
		if(!opponents.isEmpty()) {
			System.out.println("Attacking");
			for(int i = 0; i < opponents.size(); i++) {
				MovementLayout.getInstance().attackObject(it, opponents.get(i));
			}
			if(it.hasSquaredScope()) {
				Map map = Game.getInstance().getMap();
				int lane = it.getLane();
				int scope = it.getScope();
				int calcDistance = it.getDistance() + (Map.cellSize / 2);
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
				MovementLayout.getInstance().explodeRegion(it, left, top, scope * 2);
				PlacementLayout.getInstance().killObject(it);
				StatusLayout.getInstance().updateLevel();
				for(int i = 0; i < opponents.size(); i++) {
					MovementLayout.getInstance().removeObject(opponents.get(i));
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

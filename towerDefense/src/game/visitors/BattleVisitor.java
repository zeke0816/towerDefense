package game.visitors;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.Item;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import javafx.util.Pair;

public class BattleVisitor implements Visitor {
	
	protected Map map;
	
	public BattleVisitor() {
		map = Game.getInstance().getMap();
	}

	@Override
	public void visit(Warrior w) {
		boolean attacked = false;
		Pair<Integer, Integer> coordinates = map.getPositions().get(w);
		int yCoordinate = coordinates.getKey();
		int xCoordinate = coordinates.getValue();
		/*try {
			MovementLayout.getInstance().moveObject(w);
		} catch (InvalidActionException | CellTakenException e1) {
			System.out.println(e1.getMessage());
		}*/
		for(int i = 1; !attacked && i <= w.getScope() && (i + xCoordinate) < map.getColumns(); i++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate + i);
			if(opponent != null) {
				attacked = opponent.attack(w);
				if(attacked) {
					MovementLayout.getInstance().attackObject(w, opponent);
				}
			}
		}
	}

	@Override
	public void visit(Enemy e) {
		boolean attacked = false;
		Pair<Integer, Integer> coordinates = map.getPositions().get(e);
		int yCoordinate = coordinates.getKey();
		int xCoordinate = coordinates.getValue();
		try {
			MovementLayout.getInstance().moveObject(e);
		} catch (InvalidActionException | CellTakenException e1) {
			System.out.println(e1.getMessage());
		}
		for(int i = 1; !attacked && i <= e.getScope() && i <= xCoordinate; i++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate - i);
			if(opponent != null) {
				attacked = opponent.attack(e);
				if(attacked) {
					MovementLayout.getInstance().attackObject(e, opponent);
				}
			}
		}
	}

	@Override
	public void visit(Item it) {
		boolean attacked = false;
		Pair<Integer, Integer> coordinates = map.getPositions().get(it);
		int yCoordinate = coordinates.getKey();
		int xCoordinate = coordinates.getValue();
		int scope = it.getScope();
		/*try {
			MovementLayout.getInstance().moveObject(e);
		} catch (InvalidActionException | CellTakenException e1) {
			System.out.println(e1.getMessage());
		}*/
		if(it.hasSquaredScope()) {
			try {
				int top = yCoordinate - scope;
				int right = xCoordinate + scope;
				int bottom = yCoordinate + scope;
				int left = xCoordinate - scope;
				
				if(top < 0) {
					top = 0;
				}
				if(left < 0) {
					left = 0;
				}
				if(right >= map.getColumns()) {
					right = map.getColumns()-1;
				}
				if(bottom >= map.getRows()) {
					bottom = map.getRows()-1;
				}
				for(int i = top; i <= bottom; i++) {
					for(int j = left; j <= right; j++) {
						GameObject opponent = map.getObjectAt(i, j);
						if(opponent != null) {
							attacked = opponent.attack(it);
							if(attacked) {
								MovementLayout.getInstance().attackObject(it, opponent);
							}
						} else {
							MovementLayout.getInstance().explodeCell(it, i, j);
						}
					}
				}
				Game.getInstance().getMap().freeCell(it);
				PlacementLayout.getInstance().killObject(it);
			} catch (InvalidActionException e1) {
				System.out.println(e1.getMessage());
			}
		} else {
			for(int i = 1; !attacked && i <= scope && (i + xCoordinate) < map.getColumns(); i++) {
				GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate + i);
				if(opponent != null) {
					attacked = opponent.attack(it);
					if(attacked) {
						MovementLayout.getInstance().attackObject(it, opponent);
					}
				}
			}
		}
	}

}

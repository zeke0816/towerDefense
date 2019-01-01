package game.visitors;

import Item.Item;
import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.GameObject;
import game.Map;
import game.characters.Enemy;
import game.characters.Warrior;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import javafx.util.Pair;

public class AttackVisitor implements Visitor {
	
	protected Map map;
	
	public AttackVisitor() {
		map = Game.getInstance().getMap();
	}

	@Override
	public void visit(Warrior w) {
		boolean attacked = false;
		Pair<Integer, Integer> coordinates = map.getPositions().get(w);
		int yCoordinate = coordinates.getKey();
		int xCoordinate = coordinates.getValue();
		for(int i = 1; !attacked && i <= w.getScope() && (i + xCoordinate) < map.getColumns(); i++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate + i);
			if(opponent != null) {
				attacked = opponent.attack(w);
				if(attacked && opponent.isDead()) {
					try {
						map.freeCell(opponent);
						PlacementLayout.getInstance().killObject(opponent);
					} catch (InvalidActionException e) {
						System.out.println(e.getMessage());
					}
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
				if(attacked && opponent.isDead()) {
					try {
						map.freeCell(opponent);
						PlacementLayout.getInstance().killObject(opponent);
					} catch (InvalidActionException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		}
	}

	@Override
	public void visit(Item i) {
		
	}

}

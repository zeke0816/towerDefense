package game.visitors;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.GameObject;
import game.Map;
import game.characters.Enemy;
import game.characters.Warrior;
import game.items.Item;
import gui.layouts.MovementLayout;
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
	public void visit(Item i) {
		boolean attacked = false;
		Pair<Integer, Integer> coordinates = map.getPositions().get(i);
		int yCoordinate = coordinates.getKey();
		int xCoordinate = coordinates.getValue();
		try {
			MovementLayout.getInstance().moveObject(i);
		} catch (InvalidActionException | CellTakenException e1) {
			System.out.println(e1.getMessage());
		}
		for(int j = 1; !attacked && j <= i.getScope() && j <= xCoordinate; j++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate - i);
			if(opponent != null) {
				attacked = opponent.attack(e);
				if(attacked) {
					MovementLayout.getInstance().attackObject(e, opponent);
				}
			}
		}
	}

}

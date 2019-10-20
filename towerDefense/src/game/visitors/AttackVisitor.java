package game.visitors;

import Item.Item;
import game.Game;
import game.GameObject;
import game.Map;
import game.characters.Enemy;
import game.characters.Warrior;
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
					PlacementLayout.getInstance().killObject(opponent);
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
		for(int i = 1; !attacked && i <= e.getScope() && i <= xCoordinate; i++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate - i);
			if(opponent != null) {
				attacked = opponent.attack(e);
				if(attacked && opponent.isDead()) {
					PlacementLayout.getInstance().killObject(opponent);
				}
			}
		}
	}

	@Override
	public void visit(Item i) {
		
	}

}

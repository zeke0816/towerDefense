package game.visitors;

import Item.Item;
import game.Game;
import game.GameObject;
import game.Map;
import game.characters.Enemy;
import game.characters.Warrior;

public class AttackVisitor implements Visitor {
	
	protected Map map;
	protected int yCoordinate;
	protected int xCoordinate;
	
	public AttackVisitor() {
		map = Game.getInstance().getMap();
	}

	@Override
	public void visit(Warrior w) {
		boolean attacked = false;
		for(int i = 1; !attacked && i <= w.getScope() && i <= xCoordinate; i++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate + i);
			if(opponent != null) {
				attacked = opponent.attack(w);
			}
		}
	}

	@Override
	public void visit(Enemy e) {
		boolean attacked = false;
		for(int i = 1; !attacked && i <= e.getScope() && i <= xCoordinate; i++) {
			GameObject opponent = map.getObjectAt(yCoordinate, xCoordinate - i);
			if(opponent != null) {
				attacked = opponent.attack(e);
			}
		}
	}

	@Override
	public void visit(Item i) {
		
	}

}

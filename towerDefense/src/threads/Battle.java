package threads;

import java.util.HashMap;
import java.util.Map.Entry;

import game.Game;
import game.GameObject;
import game.Map;
import game.visitors.AttackVisitor;
import javafx.util.Pair;

public class Battle implements Runnable {
	
	protected AttackVisitor attack;

	@Override
	public void run() {
		Map map = Game.getInstance().getMap();
		attack = new AttackVisitor();
		try {
			while(!Game.getInstance().isOver()) {
				HashMap<GameObject, Pair<Integer, Integer>> positions = map.getPositions();
				for(Entry<GameObject, Pair<Integer, Integer>> position: positions.entrySet()) {
					GameObject object = position.getKey();
					if(object.isAlive()) {
						object.accept(attack);
					}
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

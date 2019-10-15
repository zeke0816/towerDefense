package gui;

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
		try {
			while(!Game.getInstance().isOver()) {
				HashMap<GameObject, Pair<Integer, Integer>> ePositions = map.getPositions();
				for(Entry<GameObject, Pair<Integer, Integer>> position: ePositions.entrySet()) {
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

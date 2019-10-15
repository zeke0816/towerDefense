package threads;

import java.util.Random;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import game.Map;
import game.characters.Enemy;
import gui.factories.EnemyFactory;
import gui.factories.enemies.EnemyPrototype;
import gui.layouts.MovementLayout;
import media.sounds.SoundPlayer;

public class Movement implements Runnable {

	@Override
	public void run() {
		try {
			Map map = Game.getInstance().getMap();
			while(!Game.getInstance().isOver()) {
				Random r = new Random();
				int newEnemyChooser = r.nextInt(1);
				if(newEnemyChooser == 1) {
					int row;
					do {
						row = r.nextInt(map.getRows());
					} while(map.getCell(row, map.getColumns()-1).isTaken());
					EnemyPrototype enemyPrototype = EnemyFactory.getInstance().createEnemy();
					Enemy enemy = enemyPrototype.getEnemy();
					map.takeCell(row, map.getColumns()-1, enemy);
					MovementLayout.getInstance().addEnemy(row, enemyPrototype.getID(), enemy);
					if(enemyPrototype.playsSound()) {
						SoundPlayer.getInstance().play(enemyPrototype.getID());
					}
				}
				MovementLayout.getInstance().moveEnemies();
				Thread.sleep(1000);
			}
		} catch(CellTakenException | InvalidActionException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

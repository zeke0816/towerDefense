package threads;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import game.Game;
import gui.layouts.MovementLayout;

public class Movement implements Runnable {

	@Override
	public void run() {
		try {
			while(!Game.getInstance().isOver()) {
				MovementLayout.getInstance().moveEnemies();
				Thread.sleep(50);
			}
		} catch(CellTakenException | InvalidActionException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

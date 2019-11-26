package visitors;

import game.Game;
import game.states.Loss;
import game.states.Paused;
import game.states.Running;
import game.states.Welcome;
import game.states.Win;
import gui.factories.EnemyFactory;
import gui.factories.difficulty.Easy;
import gui.layouts.DroppingLayout;
import gui.layouts.InventoryLayout;
import gui.layouts.MovementLayout;
import gui.layouts.StatusLayout;
import gui.layouts.StoreLayout;
import gui.scenes.MainScene;

/**
 * Class that defines what to do when the user decides to trigger a "start new game" command
 * @author zeke0816
 *
 */
public class StartNewGame implements GameVisitor {

	/**
	 * Initializes an empty visitor
	 */
	public StartNewGame() {
		
	}

	@Override
	public void visit(Running r) {
		// DO NOT start a new game while the game is running, pause it first.
		System.out.println("The game is running, please pause it before starting a new game.");
	}

	@Override
	public void visit(Paused r) {
		start(r.getGame());
	}

	@Override
	public void visit(Loss r) {
		start(r.getGame());
	}

	@Override
	public void visit(Win r) {
		start(r.getGame());
	}

	@Override
	public void visit(Welcome r) {
		start(r.getGame());
	}
	
	/**
	 * Starts the new game
	 * @param g the Game
	 */
	private void start(Game g) {
		MovementLayout.getInstance().flush();
		DroppingLayout.getInstance().flush();
		StoreLayout.getInstance().updateAvailability();
		InventoryLayout.getInstance().updateAvailability();
		StatusLayout.getInstance().updateScore();
		StatusLayout.getInstance().updateLevel();
		EnemyFactory.getInstance().changeState(new Easy(EnemyFactory.getInstance()));
		g.startNew();
		Running running = new Running(g);
		g.changeState(running);
		MainScene.getInstance().resume();
	}

}

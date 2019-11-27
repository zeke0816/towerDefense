package visitors;

import game.Game;
import game.states.Credits;
import game.states.Loss;
import game.states.Paused;
import game.states.Running;
import game.states.Welcome;
import game.states.Win;

/**
 * Class that defines what to do when the "Show Welcome" command is triggered
 * @author zeke0816
 *
 */
public class ShowWelcome implements GameVisitor {

	/**
	 * Initializes an empty visitor
	 */
	public ShowWelcome() {
		
	}

	@Override
	public void visit(Running r) {
		System.out.println("Finish the game to show the welcome screen.");
	}

	@Override
	public void visit(Paused r) {
		System.out.println("Finish the game to show the welcome screen.");
	}

	@Override
	public void visit(Loss r) {
		Welcome state = new Welcome(Game.getInstance());
		Game.getInstance().changeState(state);
	}

	@Override
	public void visit(Win r) {
		Welcome state = new Welcome(Game.getInstance());
		Game.getInstance().changeState(state);
	}

	@Override
	public void visit(Welcome r) {
		// Already in the Welcome state
	}

	@Override
	public void visit(Credits r) {
		Welcome state = new Welcome(Game.getInstance());
		Game.getInstance().changeState(state);
	}

}

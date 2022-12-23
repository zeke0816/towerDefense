package visitors;

import game.Game;
import game.states.Credits;
import game.states.Loss;
import game.states.Paused;
import game.states.Running;
import game.states.Welcome;
import game.states.Win;

/**
 * Class that defines what to do when the "Show Credits" command is triggered
 * @author zeke0816
 *
 */
public class ShowCredits implements GameVisitor {

	/**
	 * Initializes an empty visitor
	 */
	public ShowCredits() {
		
	}

	@Override
	public void visit(Running r) {
		System.out.println("Finish the game to show the credits screen.");
	}

	@Override
	public void visit(Paused r) {
		System.out.println("Finish the game to show the credits screen.");
	}

	@Override
	public void visit(Loss r) {
		Credits state = new Credits(Game.getInstance());
		Game.getInstance().changeState(state);
	}

	@Override
	public void visit(Win r) {
		Credits state = new Credits(Game.getInstance());
		Game.getInstance().changeState(state);
	}

	@Override
	public void visit(Welcome r) {
		Credits state = new Credits(Game.getInstance());
		Game.getInstance().changeState(state);
	}

	@Override
	public void visit(Credits r) {
		// Already in the Credits state
	}

}

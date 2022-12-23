package game.states;

import game.Game;
import visitors.GameVisitor;

/**
 * Running state of the game, which means it is still ongoing
 * @author zeke0816
 *
 */
public class Running extends GameState {

	/**
	 * Initializes the state knowing the game it is working on
	 * @param g
	 */
	public Running(Game g) {
		super(g);
	}

	@Override
	public void accept(GameVisitor v) {
		v.visit(this);
	}

}

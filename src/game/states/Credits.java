package game.states;

import game.Game;
import visitors.GameVisitor;

public class Credits extends GameState {

	public Credits(Game g) {
		super(g);
	}

	@Override
	public void accept(GameVisitor v) {
		v.visit(this);
	}

}

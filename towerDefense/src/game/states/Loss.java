package game.states;

import game.Game;
import visitors.GameVisitor;

public class Loss extends GameState {

	public Loss(Game o) {
		super(o);
	}

	@Override
	public void accept(GameVisitor v) {
		v.visit(this);
	}

}

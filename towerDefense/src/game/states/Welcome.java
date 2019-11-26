package game.states;

import game.Game;
import visitors.GameVisitor;

public class Welcome extends GameState {

	public Welcome(Game o) {
		super(o);
	}

	@Override
	public void accept(GameVisitor v) {
		v.visit(this);
	}

}

package game.states;

import game.Game;
import visitors.GameVisitor;

public class Win extends GameState {

	public Win(Game o) {
		super(o);
	}

	@Override
	public void accept(GameVisitor v) {
		v.visit(this);
	}

}

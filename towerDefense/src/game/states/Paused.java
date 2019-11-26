package game.states;

import game.Game;
import visitors.GameVisitor;

public class Paused extends GameState {

	public Paused(Game o) {
		super(o);
	}

	@Override
	public void accept(GameVisitor v) {
		v.visit(this);
	}

}

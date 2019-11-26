package visitors;

import game.states.GameState;
import game.states.Loss;
import game.states.Paused;
import game.states.Running;
import game.states.Welcome;
import game.states.Win;
import gui.scenes.MainScene;

public class PauseResumeGame implements GameVisitor {

	public PauseResumeGame() {
	}

	@Override
	public void visit(Running r) {
		GameState state = new Paused(r.getGame());
		r.getGame().changeState(state);
		MainScene.getInstance().pause();
	}

	@Override
	public void visit(Paused r) {
		GameState state = new Running(r.getGame());
		r.getGame().changeState(state);
		MainScene.getInstance().resume();
	}

	@Override
	public void visit(Loss r) {
		System.out.println("The game cannot be paused or resumed if it is not currently running or paused.");
	}

	@Override
	public void visit(Win r) {
		System.out.println("The game cannot be paused or resumed if it is not currently running or paused.");
	}

	@Override
	public void visit(Welcome r) {
		System.out.println("The game cannot be paused or resumed if it is not currently running or paused.");
	}

}

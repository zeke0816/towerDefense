package visitors;

import game.states.Running;
import game.states.Welcome;
import game.states.Paused;
import game.states.Loss;
import game.states.Win;

/**
 * Visitor Interface, contains the declaration of all possible Game states
 */
public interface GameVisitor {
	
	/**
	 * Visits the running state
	 * @param r the running state
	 */
	public void visit(Running r);

	/**
	 * Visits the paused state
	 * @param r the paused state
	 */
	public void visit(Paused r);

	/**
	 * Visits the loss state
	 * @param r the loss state
	 */
	public void visit(Loss r);

	/**
	 * Visits the win state
	 * @param r the win state
	 */
	public void visit(Win r);

	/**
	 * Visits the welcome state
	 * @param r the welcome state
	 */
	public void visit(Welcome r);

}

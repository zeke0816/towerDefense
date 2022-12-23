package visitors;

import java.util.ArrayList;

import game.objects.GameObject;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.states.Credits;
import game.states.Loss;
import game.states.Paused;
import game.states.Running;
import game.states.Welcome;
import game.states.Win;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import gui.layouts.StatusLayout;
import gui.scenes.MainScene;

/**
 * Class that defines what to do when the game is at some state
 * @author zeke0816
 *
 */
public class GameActionVisitor implements GameVisitor {
	
	/**
	 * Initializes an empty visitor
	 */
	public GameActionVisitor() {
		
	}

	@Override
	public void visit(Running r) {
		ArrayList<TemporaryCharm> wornOffCharms = r.getGame().checkTemporaryCharms();
		for(TemporaryCharm charm: wornOffCharms) {
			GameObject object = charm.getObject();
			if(object.isAlive()) {
				MovementLayout.getInstance().wearCharmOff(object);
			}
		}
		PlacementLayout.getInstance().spawnItem();
		PlacementLayout.getInstance().spawnEnemy();
		r.getGame().fight();
		StatusLayout.getInstance().updateBudget();
	}

	@Override
	public void visit(Paused r) {
		MainScene.getInstance().pause();
	}

	@Override
	public void visit(Loss r) {
		MainScene.getInstance().loss();
	}

	@Override
	public void visit(Win r) {
		MainScene.getInstance().victory();
	}

	@Override
	public void visit(Welcome r) {
		MainScene.getInstance().welcome();
	}

	@Override
	public void visit(Credits r) {
		MainScene.getInstance().credits();
	}

}

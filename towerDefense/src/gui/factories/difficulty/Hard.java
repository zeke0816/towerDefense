package gui.factories.difficulty;

import java.util.ArrayList;

import game.Game;
import game.states.GameState;
import game.states.Win;
import gui.factories.EnemyFactory;
import gui.factories.prototypes.ObjectPrototype;

public class Hard extends DifficultyState {

	protected Hard(EnemyFactory ef) {
		super(ef);
	}

	public void doThis() {
		// WIN
		GameState win = new Win(Game.getInstance());
		Game.getInstance().changeState(win);
	}

	public ObjectPrototype createEnemy(ArrayList<ObjectPrototype> le , ArrayList<ObjectPrototype> lm , ArrayList<ObjectPrototype> lh) {
		ArrayList<ObjectPrototype> selectedList;
		
		if(typeEnemy < 10) {
			selectedList = le;
		}
		else if (typeEnemy < 30) {
			selectedList = lm;
		}
		else {
			selectedList = lh;
		}
		return selectedList.get(r.nextInt(selectedList.size()));
	}

}

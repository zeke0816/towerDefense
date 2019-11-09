package gui.factories.difficulty;

import java.util.ArrayList;

import game.Game;
import gui.factories.EnemyFactory;
import gui.factories.prototypes.ObjectPrototype;

public class Hard extends DifficultyState {

	protected Hard(EnemyFactory ef) {
		super(ef);
	}

	public void doThis() {
		Game.getInstance().beat();
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

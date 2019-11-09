package gui.factories.difficulty;

import java.util.ArrayList;

import gui.factories.EnemyFactory;
import gui.factories.prototypes.ObjectPrototype;

public class Medium extends DifficultyState {

	protected Medium(EnemyFactory ef) {
		super(ef);
	}
	
	public void doThis() {
		factory.changeState(new Hard(factory));
	}
	
	public ObjectPrototype createEnemy(ArrayList<ObjectPrototype> le, ArrayList<ObjectPrototype> lm, ArrayList<ObjectPrototype> lh) {
		ArrayList<ObjectPrototype> selectedList;
		
		if(typeEnemy < 25) {
			selectedList = le;
		}
		else if (typeEnemy < 75) {
			selectedList = lm;
		}
		else {
			selectedList = lh;
		}
		return selectedList.get(r.nextInt(selectedList.size()));
	}

}

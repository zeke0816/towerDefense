package gui.factories.difficulty;

import java.util.ArrayList;
import gui.factories.EnemyFactory;
import gui.factories.prototypes.ObjectPrototype;

public class Easy extends DifficultyState {

	public Easy(EnemyFactory ef) {
		super(ef);
	}

	public void doThis() {
		factory.changeState(new Medium(factory));
	}
	
	public ObjectPrototype createEnemy(ArrayList<ObjectPrototype> le , ArrayList<ObjectPrototype> lm , ArrayList<ObjectPrototype> lh) {
		ArrayList<ObjectPrototype> selectedList;
		
		if(typeEnemy < 70) {
			selectedList = le;
		}
		else if (typeEnemy < 90) {
			selectedList = lm;
		}
		else {
			selectedList = lh;
		}
		
		return selectedList.get(r.nextInt(selectedList.size()));
	}
}

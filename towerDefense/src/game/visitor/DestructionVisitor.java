package game.visitor;

import Item.Item;
import characters.Enemy;
import characters.Warrior;
import game.Cell;
import game.Game;
import game.GameObject;
import game.Visitor;

/**
 * Class responsible for the destruction of the Logic GameObjects 
 */
//-----------------------------------------------------------------------------------------------
//TODO: finish methods
//-----------------------------------------------------------------------------------------------

public class DestructionVisitor implements Visitor{
	
	public DestructionVisitor() {
		
	}
	

	/**
	 * Destroys the game object
	 * @param g game object
	 */
	public void destroy(GameObject g) {
		g.accept(this);
	}
	
	/**
	 * Eliminates the enemy from the logic
	 * Updates the score
	 *
	 *///TODO: ENEMY ELIMINATION FROM LOGIC 
	public void visitEnemy(Enemy e) {
		Game.getInstance().updateScore(e.getPoints());
		//Game.getInstance().getMap().
	}

	@Override
	public void visitWarrior(Warrior w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitItem(Item i) {
		// TODO Auto-generated method stub
		
	}

}

package game.visitors;

import exceptions.CellTakenException;
import exceptions.PowerUpException;
import game.Game;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import gui.factories.items.ItemPrototype;
import gui.factories.warriors.WarriorPrototype;
import gui.layouts.MapLayout;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import media.sounds.SoundPlayer;

public class PlacementVisitor implements Visitor {
	
	protected GameObject object;
	protected int row;
	protected int col;

	@Override
	public void visit(Enemy e) {
		System.out.println("An Enemy cannot be applied to a Game Object.");
	}

	@Override
	public void visit(Warrior w) {
		try {
			Game.getInstance().getMap().takeCell(row, col, w);
			WarriorPrototype selectedWarrior = PlacementLayout.getInstance().getSelectedWarrior();
			if(selectedWarrior.playsSound()) {
				SoundPlayer.getInstance().play(selectedWarrior.getID());
			}
			MovementLayout.getInstance().addObject(row, col, selectedWarrior.getID(), w);
			// TODO: if there are warriors of the same type available in the inventory, do not reset the cursor nor deselect the warrior
			PlacementLayout.getInstance().deselectWarrior();
			MapLayout.getInstance().allowPicking();
		} catch(CellTakenException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void visit(PermanentCharm p) {
		try {
			if(object == null) {
				throw new PowerUpException("A Spell cannot be placed on the map.");
			}
			if(object.applyItem(p)) {
				// TODO: if there are items of the same type available in the inventory, do not reset the cursor nor deselect the item
				PlacementLayout.getInstance().deselectItem();
				MapLayout.getInstance().allowPicking();
			}
		} catch(PowerUpException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void visit(TemporaryCharm t) {
		try {
			if(object == null) {
				throw new PowerUpException("A Spell cannot be placed on the map.");
			}
			if(object.applyItem(t)) {
				MovementLayout.getInstance().applyCharm(object, PlacementLayout.getInstance().getSelectedItem().getKey());
				// TODO: if there are items of the same type available in the inventory, do not reset the cursor nor deselect the item
				PlacementLayout.getInstance().deselectItem();
				MapLayout.getInstance().allowPicking();
			}
		} catch(PowerUpException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void visit(KillableItem k) {
		try {
			Game.getInstance().getMap().takeCell(row, col, k);
			ItemPrototype selectedItem = PlacementLayout.getInstance().getSelectedItem();
			if(selectedItem.playsSound()) {
				SoundPlayer.getInstance().play(selectedItem.getID());
			}
			MovementLayout.getInstance().addObject(row, col, selectedItem.getID(), k);
			// TODO: if there are warriors of the same type available in the inventory, do not reset the cursor nor deselect the warrior
			PlacementLayout.getInstance().deselectWarrior();
			MapLayout.getInstance().allowPicking();
		} catch(CellTakenException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setObject(GameObject o, int r, int c) {
		object = o;
		row = r;
		col = c;
	}

}

package game.visitors;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import exceptions.PowerUpException;
import game.Game;
import game.Inventory;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import gui.factories.items.ItemPrototype;
import gui.factories.warriors.WarriorPrototype;
import gui.layouts.InventoryLayout;
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
			Inventory inventory = Game.getInstance().getInventory();
			WarriorPrototype selectedWarrior = PlacementLayout.getInstance().getSelectedWarrior();
			inventory.take(selectedWarrior.getID());
			Game.getInstance().getMap().takeCell(row, col, w);
			if(selectedWarrior.playsSound()) {
				SoundPlayer.getInstance().play(selectedWarrior.getID());
			}
			MovementLayout.getInstance().addObject(row, col, selectedWarrior.getID(), w);
			if(!inventory.available(selectedWarrior.getID())) {
				PlacementLayout.getInstance().deselectWarrior();
				MapLayout.getInstance().allowPicking();
			}
			InventoryLayout.getInstance().updateAvailability();
		} catch(InvalidActionException e) {
			System.out.println(e.getMessage());
		} catch (CellTakenException e) {
			System.out.println(e.getMessage());
			Game.getInstance().getInventory().add(PlacementLayout.getInstance().getSelectedItem().getID());
		}
	}

	@Override
	public void visit(PermanentCharm p) {
		try {
			if(object == null) {
				throw new PowerUpException("A Spell cannot be placed on the map.");
			}
			if(object.applyItem(p)) {
				ItemPrototype selectedItem = PlacementLayout.getInstance().getSelectedItem();
				Inventory inventory = Game.getInstance().getInventory();
				inventory.take(selectedItem.getID());
				// TODO: check if the following line is necessary
				MovementLayout.getInstance().applyCharm(object, PlacementLayout.getInstance().getSelectedItem().getKey());
				if(!inventory.available(selectedItem.getID())) {
					PlacementLayout.getInstance().deselectItem();
					MapLayout.getInstance().allowPicking();
				}
				InventoryLayout.getInstance().updateAvailability();
			}
		} catch(PowerUpException | InvalidActionException e) {
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
				ItemPrototype selectedItem = PlacementLayout.getInstance().getSelectedItem();
				Inventory inventory = Game.getInstance().getInventory();
				inventory.take(selectedItem.getID());
				MovementLayout.getInstance().applyCharm(object, PlacementLayout.getInstance().getSelectedItem().getKey());
				if(!inventory.available(selectedItem.getID())) {
					PlacementLayout.getInstance().deselectItem();
					MapLayout.getInstance().allowPicking();
				}
				InventoryLayout.getInstance().updateAvailability();
			}
		} catch(PowerUpException | InvalidActionException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void visit(KillableItem k) {
		try {
			ItemPrototype selectedItem = PlacementLayout.getInstance().getSelectedItem();
			Inventory inventory = Game.getInstance().getInventory();
			inventory.take(selectedItem.getID());
			Game.getInstance().getMap().takeCell(row, col, k);
			if(selectedItem.playsSound()) {
				SoundPlayer.getInstance().play(selectedItem.getID());
			}
			MovementLayout.getInstance().addObject(row, col, selectedItem.getID(), k);
			if(!inventory.available(selectedItem.getID())) {
				PlacementLayout.getInstance().deselectItem();
				MapLayout.getInstance().allowPicking();
			}
			InventoryLayout.getInstance().updateAvailability();
		} catch(InvalidActionException e) {
			System.out.println(e.getMessage());
		} catch (CellTakenException e) {
			System.out.println(e.getMessage());
			Game.getInstance().getInventory().add(PlacementLayout.getInstance().getSelectedItem().getID());
		}
	}
	
	public void setObject(GameObject o, int r, int c) {
		object = o;
		row = r;
		col = c;
	}

}

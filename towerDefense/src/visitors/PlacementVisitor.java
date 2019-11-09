package visitors;

import exceptions.CellTakenException;
import exceptions.InvalidActionException;
import exceptions.PowerUpException;
import exceptions.UnavailableObjectException;
import game.Game;
import game.objects.GameObject;
import game.objects.characters.enemies.Enemy;
import game.objects.characters.warriors.Warrior;
import game.objects.items.charm.permanent.PermanentCharm;
import game.objects.items.charm.temporary.TemporaryCharm;
import game.objects.items.killable.KillableItem;
import gui.layouts.InventoryLayout;
import gui.layouts.MapLayout;
import gui.layouts.MovementLayout;
import gui.layouts.PlacementLayout;
import media.sounds.SoundPlayer;

public class PlacementVisitor implements Visitor {
	
	protected GameObject object;
	protected int lane;
	protected int distance;
	
	public PlacementVisitor(int l, int d) {
		lane = l;
		distance = d;
		object = Game.getInstance().getMap().getObjectAt(lane, distance);
	}

	@Override
	public void visit(Enemy e) {
		System.out.println("An Enemy cannot be applied to a Game Object.");
	}

	@Override
	public void visit(Warrior w) {
		try {
			boolean moreAvailable = w.use();
			Game.getInstance().getMap().takeCell(w);
			if(w.playsSound()) {
				SoundPlayer.getInstance().play(w.getID());
			}
			MovementLayout.getInstance().addObject(w);
			if(!moreAvailable) {
				PlacementLayout.getInstance().deselectObject();
				MapLayout.getInstance().allowPicking();
			}
			InventoryLayout.getInstance().updateAvailability();
		} catch(InvalidActionException | UnavailableObjectException e) {
			System.out.println(e.getMessage());
		} catch (CellTakenException e) {
			System.out.println(e.getMessage());
			w.save();
		}
	}

	@Override
	public void visit(PermanentCharm p) {
		try {
			if(object == null) {
				throw new PowerUpException("A Spell cannot be placed on the map.");
			}
			if(object.applyItem(p)) {
				boolean moreAvailable = p.use();
				MovementLayout.getInstance().applyCharm(object, p.getKey());
				if(!moreAvailable) {
					PlacementLayout.getInstance().deselectObject();
					MapLayout.getInstance().allowPicking();
				}
				InventoryLayout.getInstance().updateAvailability();
			}
		} catch(PowerUpException | UnavailableObjectException e) {
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
				boolean moreAvailable = t.use();
				MovementLayout.getInstance().applyCharm(object, t.getKey());
				if(!moreAvailable) {
					PlacementLayout.getInstance().deselectObject();
					MapLayout.getInstance().allowPicking();
				}
				InventoryLayout.getInstance().updateAvailability();
			}
		} catch(PowerUpException | UnavailableObjectException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void visit(KillableItem k) {
		try {
			boolean moreAvailable = k.use();
			Game.getInstance().getMap().takeCell(k);
			if(k.playsSound()) {
				SoundPlayer.getInstance().play(k.getID());
			}
			MovementLayout.getInstance().addObject(k);
			if(!moreAvailable) {
				PlacementLayout.getInstance().deselectObject();
				MapLayout.getInstance().allowPicking();
			}
			InventoryLayout.getInstance().updateAvailability();
		} catch(InvalidActionException | UnavailableObjectException e) {
			System.out.println(e.getMessage());
		} catch (CellTakenException e) {
			System.out.println(e.getMessage());
			k.save();
		}
	}

}

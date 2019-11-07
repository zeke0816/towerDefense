package gui.layouts;

import game.Game;
import game.Inventory;
import gui.factories.items.BarricadePrototype;
import gui.factories.items.CurePrototype;
import gui.factories.items.NukePrototype;
import gui.factories.items.PoisonPrototype;
import gui.factories.items.ShieldPrototype;
import gui.factories.warriors.AgentPPrototype;
import gui.factories.warriors.BB8Prototype;
import gui.factories.warriors.CyborgPrototype;
import gui.factories.warriors.GaryPrototype;
import gui.factories.warriors.TheFleaPrototype;
import gui.factories.warriors.TurretPrototype;
import gui.scenes.MainScene;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class InventoryLayout extends Layout<GridPane> {
	
	protected static final Background pinkBackground = new Background(new BackgroundFill(Paint.valueOf("#ff8d7f"), null, null));
	protected static final Background redBackground = new Background(new BackgroundFill(Paint.valueOf("#e55341"), null, null));
	private static final InventoryLayout instance = new InventoryLayout();
	private BB8Prototype bb8;
	private TheFleaPrototype flea;
	private GaryPrototype gary;
	private TurretPrototype turret;
	private AgentPPrototype agentP;
	private CyborgPrototype cyborg;
	private Label separator;
	private ShieldPrototype shield;
	private CurePrototype cure;
	private PoisonPrototype poison;
	private BarricadePrototype barricade;
	private NukePrototype nuke;
	
	public InventoryLayout() {
		super();
		
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(120);
        layout.setBackground(redBackground);
        layout.setOnKeyPressed(deslectObjectListener);
        
        bb8 = new BB8Prototype();
        flea = new TheFleaPrototype();
        gary = new GaryPrototype();
        turret = new TurretPrototype();
        agentP = new AgentPPrototype();
        cyborg = new CyborgPrototype();
        shield = new ShieldPrototype();
        cure = new CurePrototype();
        poison = new PoisonPrototype();
        barricade = new BarricadePrototype();
        nuke = new NukePrototype();

        separator = new Label();
        separator.setVisible(true);
        separator.setPrefSize(10, 64);
        separator.setBackground(pinkBackground);
        GridPane.setMargin(separator, new Insets(5, 5, 5, 5));
        
        updateAvailability();
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static InventoryLayout getInstance() {
		return instance;
	}
	
	/**
	 * Updates the graphical interface depicting the availability of each Game Object
	 */
	public void updateAvailability() {
		layout.getChildren().clear();
		Inventory inventory = Game.getInstance().getInventory();
		
		layout.add(bb8.getDisabledLabel(), 0, 0);
		if(inventory.available(bb8.getID())) {
			layout.add(bb8.getPlacingButton(), 0, 0);
		}

		layout.add(flea.getDisabledLabel(), 1, 0);
		if(inventory.available(flea.getID())) {
			layout.add(flea.getPlacingButton(), 1, 0);
		}

		layout.add(gary.getDisabledLabel(), 2, 0);
		if(inventory.available(gary.getID())) {
			layout.add(gary.getPlacingButton(), 2, 0);
		}

		layout.add(turret.getDisabledLabel(), 3, 0);
		if(inventory.available(turret.getID())) {
			layout.add(turret.getPlacingButton(), 3, 0);
		}

		layout.add(agentP.getDisabledLabel(), 4, 0);
		if(inventory.available(agentP.getID())) {
			layout.add(agentP.getPlacingButton(), 4, 0);
		}

		layout.add(cyborg.getDisabledLabel(), 5, 0);
		if(inventory.available(cyborg.getID())) {
			layout.add(cyborg.getPlacingButton(), 5, 0);
		}
		
        layout.add(separator, 6, 0);

		layout.add(shield.getDisabledLabel(), 7, 0);
		if(inventory.available(shield.getID())) {
			layout.add(shield.getPlacingButton(), 7, 0);
		}

		layout.add(cure.getDisabledLabel(), 8, 0);
		if(inventory.available(cure.getID())) {
			layout.add(cure.getPlacingButton(), 8, 0);
		}

		layout.add(poison.getDisabledLabel(), 9, 0);
		if(inventory.available(poison.getID())) {
			layout.add(poison.getPlacingButton(), 9, 0);
		}

		layout.add(barricade.getDisabledLabel(), 10, 0);
		if(inventory.available(barricade.getID())) {
			layout.add(barricade.getPlacingButton(), 10, 0);
		}

		layout.add(nuke.getDisabledLabel(), 11, 0);
		if(inventory.available(nuke.getID())) {
			layout.add(nuke.getPlacingButton(), 11, 0);
    	}
	}
	
	/**
	 * Listener for deselecting a warrior
	 */
	private EventHandler<KeyEvent> deslectObjectListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			if(key.getCode() == KeyCode.ESCAPE) {
				PlacementLayout.getInstance().deselectWarrior();
				PlacementLayout.getInstance().deselectItem();
				MainScene.getInstance().resetCursorImage();
				MapLayout.getInstance().allowPicking();
			}
		}
		
	};

}

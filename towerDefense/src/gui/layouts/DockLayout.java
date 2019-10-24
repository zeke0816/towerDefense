package gui.layouts;

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
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class DockLayout extends Layout<GridPane> {
	
	protected static final Background darkBackground = new Background(new BackgroundFill(Paint.valueOf("#dddddd"), null, null));
	private static final DockLayout instance = new DockLayout();

	protected DockLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(120);
        layout.setBackground(darkBackground);
        layout.setOnKeyPressed(deslectWarriorListener);
        
        AgentPPrototype agentP = new AgentPPrototype();
        layout.add(agentP.getButton(), 0, 0);
        layout.add(agentP.getNameLabel(), 0, 1);
        layout.add(agentP.getPriceLabel(), 0, 2);
        
        TheFleaPrototype flea = new TheFleaPrototype();
        layout.add(flea.getButton(), 1, 0);
        layout.add(flea.getNameLabel(), 1, 1);
        layout.add(flea.getPriceLabel(), 1, 2);

        CyborgPrototype cyborg = new CyborgPrototype();
        layout.add(cyborg.getButton(), 2, 0);
        layout.add(cyborg.getNameLabel(), 2, 1);
        layout.add(cyborg.getPriceLabel(), 2, 2);
        
        BB8Prototype bb8 = new BB8Prototype();
        layout.add(bb8.getButton(), 3, 0);
        layout.add(bb8.getNameLabel(), 3, 1);
        layout.add(bb8.getPriceLabel(), 3, 2);
        
        GaryPrototype gary = new GaryPrototype();
        layout.add(gary.getButton(), 4, 0);
        layout.add(gary.getNameLabel(), 4, 1);
        layout.add(gary.getPriceLabel(), 4, 2);
        
        TurretPrototype turret = new TurretPrototype();
        layout.add(turret.getButton(), 5, 0);
        layout.add(turret.getNameLabel(), 5, 1);
        layout.add(turret.getPriceLabel(), 5, 2);
        
        NukePrototype nuke = new NukePrototype();
        layout.add(nuke.getButton(), 6, 0);
        layout.add(nuke.getNameLabel(), 6, 1);
        layout.add(nuke.getPriceLabel(), 6, 2);
        
        CurePrototype cure = new CurePrototype();
        layout.add(cure.getButton(), 7, 0);
        layout.add(cure.getNameLabel(), 7, 1);
        layout.add(cure.getPriceLabel(), 7, 2);
        
        PoisonPrototype poison = new PoisonPrototype();
        layout.add(poison.getButton(), 8, 0);
        layout.add(poison.getNameLabel(), 8, 1);
        layout.add(poison.getPriceLabel(), 8, 2);
        
        ShieldPrototype shield = new ShieldPrototype();
        layout.add(shield.getButton(), 9, 0);
        layout.add(shield.getNameLabel(), 9, 1);
        layout.add(shield.getPriceLabel(), 9, 2);
        
        BarricadePrototype barricade = new BarricadePrototype();
        layout.add(barricade.getButton(), 10, 0);
        layout.add(barricade.getNameLabel(), 10, 1);
        layout.add(barricade.getPriceLabel(), 10, 2);
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static DockLayout getInstance() {
		return instance;
	}
	
	/**
	 * Listener for deselecting a warrior
	 */
	private EventHandler<KeyEvent> deslectWarriorListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			if(key.getCode() == KeyCode.ESCAPE) {
				PlacementLayout.getInstance().deselectWarrior();
				MainScene.getInstance().resetCursorImage();
				MapLayout.getInstance().allowPicking();
			}
		}
		
	};

}

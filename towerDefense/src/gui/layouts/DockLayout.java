package gui.layouts;

import game.factories.characters.warriors.AgentPPrototype;
import game.factories.characters.warriors.BB8Prototype;
import game.factories.characters.warriors.CyborgPrototype;
import game.factories.characters.warriors.GaryPrototype;
import game.factories.characters.warriors.TheFleaPrototype;
import game.factories.characters.warriors.TurretPrototype;
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

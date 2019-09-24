package gui.layouts;

import gui.factories.warriors.AgentPPrototype;
import gui.factories.warriors.BB8Prototype;
import gui.factories.warriors.CyborgPrototype;
import gui.factories.warriors.GaryPrototype;
import gui.factories.warriors.TheFleaPrototype;
import gui.factories.warriors.TophPrototype;
import gui.factories.warriors.TurretPrototype;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class DockLayout extends Layout<GridPane> {
	
	private static final DockLayout instance = new DockLayout();

	protected DockLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(120);
        
        AgentPPrototype agentP = new AgentPPrototype();
        layout.add(agentP.getButton(), 0, 0);
        layout.add(agentP.getLabel(), 0, 1);
        
        TheFleaPrototype flea = new TheFleaPrototype();
        layout.add(flea.getButton(), 1, 0);
        layout.add(flea.getLabel(), 1, 1);

        CyborgPrototype cyborg = new CyborgPrototype();
        layout.add(cyborg.getButton(), 2, 0);
        layout.add(cyborg.getLabel(), 2, 1);
        
        BB8Prototype bb8 = new BB8Prototype();
        layout.add(bb8.getButton(), 3, 0);
        layout.add(bb8.getLabel(), 3, 1);
        
        GaryPrototype gary = new GaryPrototype();
        layout.add(gary.getButton(), 4, 0);
        layout.add(gary.getLabel(), 4, 1);
        
        TurretPrototype turret = new TurretPrototype();
        layout.add(turret.getButton(), 5, 0);
        layout.add(turret.getLabel(), 5, 1);
        
        TophPrototype toph = new TophPrototype();
        layout.add(toph.getButton(), 6, 0);
        layout.add(toph.getLabel(), 6, 1);
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static DockLayout getInstance() {
		return instance;
	}

}

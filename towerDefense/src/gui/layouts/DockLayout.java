package gui.layouts;

import exceptions.DatabaseException;
import gui.controls.WarriorButton;
import gui.factories.warriors.AgentPPrototype;
import gui.factories.warriors.BB8Prototype;
import gui.factories.warriors.CyborgPrototype;
import gui.factories.warriors.GaryPrototype;
import gui.factories.warriors.TheFleaPrototype;
import gui.factories.warriors.TophPrototype;
import gui.factories.warriors.TurretPrototype;
import gui.factories.warriors.WarriorPrototype;
import gui.scenes.MainScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import media.databases.MediaDatabase;

public class DockLayout extends Layout<GridPane> {
	
	private static final DockLayout instance = new DockLayout();

	protected DockLayout() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(120);
        
        AgentPPrototype agentP = new AgentPPrototype();
        agentP.getButton().setOnAction(selectWarriorListener);
        layout.add(agentP.getButton(), 0, 0);
        layout.add(agentP.getLabel(), 0, 1);
        
        TheFleaPrototype flea = new TheFleaPrototype();
        flea.getButton().setOnAction(selectWarriorListener);
        layout.add(flea.getButton(), 1, 0);
        layout.add(flea.getLabel(), 1, 1);

        CyborgPrototype cyborg = new CyborgPrototype();
        cyborg.getButton().setOnAction(selectWarriorListener);
        layout.add(cyborg.getButton(), 2, 0);
        layout.add(cyborg.getLabel(), 2, 1);
        
        BB8Prototype bb8 = new BB8Prototype();
        bb8.getButton().setOnAction(selectWarriorListener);
        layout.add(bb8.getButton(), 3, 0);
        layout.add(bb8.getLabel(), 3, 1);
        
        GaryPrototype gary = new GaryPrototype();
        gary.getButton().setOnAction(selectWarriorListener);
        layout.add(gary.getButton(), 4, 0);
        layout.add(gary.getLabel(), 4, 1);
        
        TurretPrototype turret = new TurretPrototype();
        turret.getButton().setOnAction(selectWarriorListener);
        layout.add(turret.getButton(), 5, 0);
        layout.add(turret.getLabel(), 5, 1);
        
        TophPrototype toph = new TophPrototype();
        toph.getButton().setOnAction(selectWarriorListener);
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
	
	/**
	 * Listener for warrior selection from the dock
	 */
	EventHandler<ActionEvent> selectWarriorListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				WarriorButton button = (WarriorButton) event.getSource();
				WarriorPrototype selectedWarrior = button.getWarrior();
				PlacementLayout.getInstance().selectWarrior(selectedWarrior);
				try {
					Image img = MediaDatabase.getInstance().getImageMedia(selectedWarrior.getID()+"cursor");
					MainScene.getInstance().setCursorImage(img);
				} catch (DatabaseException e) {
					System.out.println("The selected Warrior's graphics could not replace the cursor.");
				}
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			}
		}
		
	};

}

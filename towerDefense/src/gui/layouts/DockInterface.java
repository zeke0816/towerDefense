package gui.layouts;

import java.io.File;

import gui.factories.warriors.AgentPInterface;
import gui.factories.warriors.WarriorInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class DockInterface extends LayoutInterface<GridPane> {
	
	private static final DockInterface instance = new DockInterface();

	protected DockInterface() {
		super();
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(120);
        
        AgentPInterface agentP = new AgentPInterface();
        agentP.getButton().setOnAction(selectWarriorListener);
        layout.add(agentP.getButton(), 0, 0);
        layout.add(agentP.getLabel(), 0, 1);
        GridPane.setHalignment(agentP.getLabel(), HPos.CENTER);
        
        /*WarriorInterface flea = new WarriorInterface("TheFlea", "The Flea", true);
        flea.getButton().setOnAction(selectWarriorListener);
        layout.add(flea.getButton(), 1, 0);
        layout.add(flea.getLabel(), 1, 1);
        GridPane.setHalignment(flea.getLabel(), HPos.CENTER);

        WarriorInterface cyborg = new WarriorInterface("Cyborg", "Cyborg", true);
        cyborg.getButton().setOnAction(selectWarriorListener);
        layout.add(cyborg.getButton(), 2, 0);
        layout.add(cyborg.getLabel(), 2, 1);
        GridPane.setHalignment(cyborg.getLabel(), HPos.CENTER);
        
        WarriorInterface bb8 = new WarriorInterface("BB8", "BB8", false);
        bb8.getButton().setOnAction(selectWarriorListener);
        layout.add(bb8.getButton(), 3, 0);
        layout.add(bb8.getLabel(), 3, 1);
        GridPane.setHalignment(bb8.getLabel(), HPos.CENTER);
        
        WarriorInterface gary = new WarriorInterface("Gary", "Gary", true);
        gary.getButton().setOnAction(selectWarriorListener);
        layout.add(gary.getButton(), 4, 0);
        layout.add(gary.getLabel(), 4, 1);
        GridPane.setHalignment(gary.getLabel(), HPos.CENTER);
        
        WarriorInterface turret = new WarriorInterface("Turret", "Turret", false);
        turret.getButton().setOnAction(selectWarriorListener);
        layout.add(turret.getButton(), 5, 0);
        layout.add(turret.getLabel(), 5, 1);
        GridPane.setHalignment(turret.getLabel(), HPos.CENTER);
        
        WarriorInterface toph = new WarriorInterface("Toph", "Toph", false);
        toph.getButton().setOnAction(selectWarriorListener);
        layout.add(toph.getButton(), 6, 0);
        layout.add(toph.getLabel(), 6, 1);
        GridPane.setHalignment(toph.getLabel(), HPos.CENTER);*/
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static DockInterface getInstance() {
		return instance;
	}
	
	/**
	 * Sets a custom image as the cursor
	 * @param img the image
	 */
	private void setCursorImage(Image img) {
		appScene.setCursor(new ImageCursor(img, img.getWidth()/2, img.getHeight()/2));
	}
	
	//TODO: Use Proxy to get Media and stuff
	
	/**
	 * Gets media information as a String from a given path
	 * @param path the path to the media file
	 * @return the media information
	 */
	private String getMediaFromPath(String path) {
		return (new File(path)).toURI().toString();
	}
	
	/**
	 * Listener for warrior selection from the dock
	 */
	EventHandler<ActionEvent> selectWarriorListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				Button warrior = (Button) event.getSource();
				WarriorInterface selectedWarrior = (WarriorInterface) warrior.getUserData();
				MapInterface.getInstance().selectWarrior(selectedWarrior);
				Image img = new Image(getMediaFromPath("src/assets/cursors/"+selectedWarrior.getID()+".png"));
				setCursorImage(img);
			} catch(ClassCastException e) {
				System.out.println("Invalid cast while selecting the warrior.");
			}
		}
		
	};

}

package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

public class WarriorInterface {
	
	protected String id;
	protected String name;
	protected boolean interactive;
	protected Label label;
	protected Button button;
	
	/**
	 * Creates a Warrior Interface with all its attributes
	 * @param i the ID of the warrior
	 * @param n the name of the warrior to be shown on the dock
	 * @param in true if it plays music when places, false if not
	 * @param l the label for the Warrior
	 * @param b the button for the Warrior
	 */
	public WarriorInterface(String i, String n, boolean in) {
		id = i;
		name = n;
		interactive = in;
		
		label = new Label(name);
		label.setVisible(true);
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font("Cambria", 20));
		
		button = new Button();
		button.setVisible(true);
        button.setPrefSize(120, 120);
        button.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/assets/"+id+".png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(240, 240, false, false, true, false))));
        button.setUserData(this);
	}
	
	public String getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean playsMusic() {
		return interactive;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public Button getButton() {
		return button;
	}

}

package gui.factories.warriors;

import java.io.File;

import characters.Warrior;
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

public class AgentPInterface extends WarriorInterface {
	
	protected int id;
	protected String name;
	protected boolean playsMusic;
	protected Label label;
	protected Button button;

	public AgentPInterface() {
		super();
		
		id = 1;
		name = "Agent P";
		playsMusic = true;
		
		// TODO: implement Proxy to get the images
		
		label = new Label(name);
		label.setVisible(true);
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font("Cambria", 20));
		
		button = new Button();
		button.setVisible(true);
        button.setPrefSize(100, 100);
        button.setBackground(new Background(new BackgroundImage(new Image(new File("src/assets/images/"+id+".png").toURI().toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(120, 120, false, false, true, false))));
        button.setUserData(this);
	}
	
	public Warrior getWarrior() {
		return warrior;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean playsMusic() {
		return playsMusic;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public Button getButton() {
		return button;
	}

}

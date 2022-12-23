package gui.layouts;

import gui.factories.prototypes.characters.warriors.TophPrototype;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class BaseLayout extends Layout<VBox> {
	
	private static final BaseLayout instance = new BaseLayout();

	protected BaseLayout() {
		super();
		layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        
        TophPrototype toph = new TophPrototype();
        layout.getChildren().add(toph.getPlacingButton());
	}
	
	public static BaseLayout getInstance() {
		return instance;
	}
	
}

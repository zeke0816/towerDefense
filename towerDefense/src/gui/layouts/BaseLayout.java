package gui.layouts;

import gui.factories.warriors.TophPrototype;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class BaseLayout extends Layout<VBox> {
	
	private static final BaseLayout instance = new BaseLayout();

	protected BaseLayout() {
		super();
		layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        
        TophPrototype toph = new TophPrototype();
        layout.getChildren().add(toph.getButton());
        layout.getChildren().add(toph.getNameLabel());
	}
	
	public static BaseLayout getInstance() {
		return instance;
	}
	
}

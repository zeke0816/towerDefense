package gui.layouts;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class LayoutInterface<E extends Pane> {
	
	protected Scene appScene;
	protected E layout;

	public LayoutInterface() {
		
	}
	
	/**
	 * Sets the scene so that the interface can communicate with it
	 * @param s the Scene
	 */
	public void setScene(Scene s) {
		appScene = s;
	}
	
	/**
	 * Gets the layout of the interface
	 * @return the layout
	 */
	public E getLayout() {
		return layout;
	}

}

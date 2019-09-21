package gui.layouts;

import javafx.scene.layout.Pane;

public abstract class LayoutInterface<E extends Pane> {
	
	protected E layout;

	protected LayoutInterface() {
		
	}
	
	/**
	 * Gets the layout of the interface
	 * @return the layout
	 */
	public E getLayout() {
		return layout;
	}

}

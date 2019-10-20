package gui.layouts;

import javafx.scene.layout.Pane;

public abstract class Layout<E extends Pane> {
	
	protected E layout;

	protected Layout() {
		
	}
	
	/**
	 * Gets the layout of the interface
	 * @return the layout
	 */
	public E getLayout() {
		return layout;
	}

}

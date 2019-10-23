package gui.controls;

import gui.factories.items.ItemPrototype;
import javafx.scene.control.Button;

public class ItemButton extends Button {
	
	protected ItemPrototype item;
	
	public ItemButton() {
		super();
	}
	
	public void setItem(ItemPrototype i) {
		item = i;
	}
	
	public ItemPrototype getItem() {
		return item;
	}

}

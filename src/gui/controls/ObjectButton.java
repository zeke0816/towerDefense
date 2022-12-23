package gui.controls;

import gui.factories.prototypes.ObjectPrototype;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ObjectButton extends Button {
	
	protected ObjectPrototype prototype;
	
	public ObjectButton(double width, double height) {
		super();
		
		setVisible(true);
        setPrefSize(width, height);
		setAlignment(Pos.CENTER);
		setFont(new Font("Trebuchet", 12));
		setCursor(Cursor.HAND);
		setTextFill(Paint.valueOf("b0f9a7"));
        setBorder(new Border(new BorderStroke(Color.valueOf("#ff8d7f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public void setPrototype(ObjectPrototype p) {
		prototype = p;
	}
	
	public ObjectPrototype getPrototype() {
		return prototype;
	}
	
}

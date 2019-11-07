package gui.controls;

import gui.factories.warriors.WarriorPrototype;
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

public class WarriorButton extends Button {
	
	protected WarriorPrototype warrior;
	
	public WarriorButton(double width, double height) {
		super();
		
		setVisible(true);
        setPrefSize(width, height);
		setAlignment(Pos.CENTER);
		setFont(new Font("Trebuchet", 12));
		setCursor(Cursor.HAND);
		setTextFill(Paint.valueOf("b0f9a7"));
        setBorder(new Border(new BorderStroke(Color.valueOf("#ff8d7f"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public void setPrototype(WarriorPrototype w) {
		warrior = w;
	}
	
	public WarriorPrototype getPrototype() {
		return warrior;
	}
	
}

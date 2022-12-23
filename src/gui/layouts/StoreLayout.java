package gui.layouts;

import game.Game;
import gui.factories.prototypes.characters.warriors.AgentPPrototype;
import gui.factories.prototypes.characters.warriors.BB8Prototype;
import gui.factories.prototypes.characters.warriors.CyborgPrototype;
import gui.factories.prototypes.characters.warriors.GaryPrototype;
import gui.factories.prototypes.characters.warriors.TheFleaPrototype;
import gui.factories.prototypes.characters.warriors.TurretPrototype;
import gui.factories.prototypes.items.BarricadePrototype;
import gui.factories.prototypes.items.CurePrototype;
import gui.factories.prototypes.items.NukePrototype;
import gui.factories.prototypes.items.PoisonPrototype;
import gui.factories.prototypes.items.ShieldPrototype;
import gui.scenes.MainScene;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class StoreLayout extends Layout<GridPane> {

	protected static final Background pinkBackground = new Background(new BackgroundFill(Paint.valueOf("#ff8d7f"), null, null));
	protected static final Background redBackground = new Background(new BackgroundFill(Paint.valueOf("#e55341"), null, null));
	private static final StoreLayout instance = new StoreLayout();
	private BB8Prototype bb8;
	private TheFleaPrototype flea;
	private GaryPrototype gary;
	private TurretPrototype turret;
	private AgentPPrototype agentP;
	private CyborgPrototype cyborg;
	private Label separator;
	private ShieldPrototype shield;
	private CurePrototype cure;
	private PoisonPrototype poison;
	private BarricadePrototype barricade;
	private NukePrototype nuke;
	
	public StoreLayout() {
		super();
		
		layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(135);
        layout.setBackground(redBackground);
        layout.setOnKeyPressed(deslectObjectListener);
        
        bb8 = new BB8Prototype();
        flea = new TheFleaPrototype();
        gary = new GaryPrototype();
        turret = new TurretPrototype();
        agentP = new AgentPPrototype();
        cyborg = new CyborgPrototype();
        shield = new ShieldPrototype();
        cure = new CurePrototype();
        poison = new PoisonPrototype();
        barricade = new BarricadePrototype();
        nuke = new NukePrototype();

        separator = new Label();
        separator.setVisible(true);
        separator.setPrefSize(10, 100);
        separator.setBackground(pinkBackground);
        GridPane.setMargin(separator, new Insets(5, 5, 5, 5));
        
        updateAvailability();
	}
	
	/**
	 * Gets the instance of this class
	 * @return the only instance of this class
	 */
	public static StoreLayout getInstance() {
		return instance;
	}

	/**
	 * Updates the graphical interface depicting the availability of each Game Object
	 */
	public void updateAvailability() {
		layout.getChildren().clear();
		Game game = Game.getInstance();
		
		layout.add(bb8.getDisabledLabel(), 0, 0);
		if(game.canAfford(bb8.getObject().getPrice())) {
			layout.add(bb8.getProfileLabel(), 0, 0);
			layout.add(bb8.getBuyingButton(), 0, 1);
			layout.add(bb8.getBuyPlaceButton(), 0, 2);
		}

		layout.add(flea.getDisabledLabel(), 1, 0);
		if(game.canAfford(flea.getObject().getPrice())) {
			layout.add(flea.getProfileLabel(), 1, 0);
			layout.add(flea.getBuyingButton(), 1, 1);
			layout.add(flea.getBuyPlaceButton(), 1, 2);
		}

		layout.add(gary.getDisabledLabel(), 2, 0);
		if(game.canAfford(gary.getObject().getPrice())) {
			layout.add(gary.getProfileLabel(), 2, 0);
			layout.add(gary.getBuyingButton(), 2, 1);
			layout.add(gary.getBuyPlaceButton(), 2, 2);
		}

		layout.add(turret.getDisabledLabel(), 3, 0);
		if(game.canAfford(turret.getObject().getPrice())) {
			layout.add(turret.getProfileLabel(), 3, 0);
			layout.add(turret.getBuyingButton(), 3, 1);
			layout.add(turret.getBuyPlaceButton(), 3, 2);
		}

		layout.add(agentP.getDisabledLabel(), 4, 0);
		if(game.canAfford(agentP.getObject().getPrice())) {
			layout.add(agentP.getProfileLabel(), 4, 0);
			layout.add(agentP.getBuyingButton(), 4, 1);
			layout.add(agentP.getBuyPlaceButton(), 4, 2);
		}

		layout.add(cyborg.getDisabledLabel(), 5, 0);
		if(game.canAfford(cyborg.getObject().getPrice())) {
			layout.add(cyborg.getProfileLabel(), 5, 0);
			layout.add(cyborg.getBuyingButton(), 5, 1);
			layout.add(cyborg.getBuyPlaceButton(), 5, 2);
		}
		
        layout.add(separator, 6, 0);

		layout.add(shield.getDisabledLabel(), 7, 0);
		if(game.canAfford(shield.getObject().getPrice())) {
			layout.add(shield.getProfileLabel(), 7, 0);
			layout.add(shield.getBuyingButton(), 7, 1);
			layout.add(shield.getBuyPlaceButton(), 7, 2);
		}

		layout.add(cure.getDisabledLabel(), 8, 0);
		if(game.canAfford(cure.getObject().getPrice())) {
			layout.add(cure.getProfileLabel(), 8, 0);
			layout.add(cure.getBuyingButton(), 8, 1);
			layout.add(cure.getBuyPlaceButton(), 8, 2);
		}

		layout.add(poison.getDisabledLabel(), 9, 0);
		if(game.canAfford(poison.getObject().getPrice())) {
			layout.add(poison.getProfileLabel(), 9, 0);
			layout.add(poison.getBuyingButton(), 9, 1);
			layout.add(poison.getBuyPlaceButton(), 9, 2);
		}

		layout.add(barricade.getDisabledLabel(), 10, 0);
		if(game.canAfford(barricade.getObject().getPrice())) {
			layout.add(barricade.getProfileLabel(), 10, 0);
			layout.add(barricade.getBuyingButton(), 10, 1);
			layout.add(barricade.getBuyPlaceButton(), 10, 2);
		}

		layout.add(nuke.getDisabledLabel(), 11, 0);
		if(game.canAfford(nuke.getObject().getPrice())) {
			layout.add(nuke.getProfileLabel(), 11, 0);
			layout.add(nuke.getBuyingButton(), 11, 1);
			layout.add(nuke.getBuyPlaceButton(), 11, 2);
    	}
	}
	
	/**
	 * Listener for deselecting a warrior
	 */
	private EventHandler<KeyEvent> deslectObjectListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			if(key.getCode() == KeyCode.ESCAPE) {
				PlacementLayout.getInstance().deselectObject();
				MainScene.getInstance().resetCursorImage();
				MapLayout.getInstance().allowPicking();
			}
		}
		
	};

}

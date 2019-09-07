package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class to handle all things related to the General User Interface
 * @author zeke0816
 *
 */
public class GUI extends Application {
	
	protected Stage appStage;
	protected Scene appScene;
	protected BorderPane appLayout;
	protected Game game;
	protected Button[][] arena;
	protected VBox arenaLayout;
	protected FlowPane dockLayout;
	private MediaPlayer mediaPlayer;
	protected static final Background darkBackground = new Background(new BackgroundFill(Paint.valueOf("#dddddd"), null, null));
	//protected static final Background background = new Background(new BackgroundFill(Paint.valueOf("#ffffff"), new CornerRadii(4), new Insets(2)));
	protected static final Paint gray = Paint.valueOf("#8e8e8e");
	protected boolean playPerry;
	private FlowPane statusLayout;
	
	/**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
    	launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		initialize(stage);
	}
	
	/**
	 * Initializes the General User Interface with an empty game and arena
	 */
	private void initialize(Stage stage) {
		appStage = stage;
		appStage.setTitle("Cartoon Defense");
		appStage.setWidth(1300);
		appStage.setHeight(700);
		appStage.centerOnScreen();
		mediaPlayer = new MediaPlayer(new Media(getMediaFromPath("/assets/AgentP.mp3")));
		
        appLayout = new BorderPane();
        appLayout.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/assets/background.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1300, 482, false, false, false, true))));
        
        appScene = new Scene(appLayout);
        appScene.getStylesheets().add(getClass().getResource("/assets/min.style.css").toExternalForm());
		
        statusLayout = new FlowPane();
        Label gameTitle = new Label("Cartoon Defense");
        gameTitle.setAlignment(Pos.CENTER);
        gameTitle.setFont(new Font("Cambria", 50));
        statusLayout.setAlignment(Pos.CENTER);
        statusLayout.getChildren().add(gameTitle);
        
        arenaLayout = new VBox();
        arenaLayout.setAlignment(Pos.CENTER);
        
		game = new Game();
		Map map = game.getMap();
		arena = new Button[map.getRows()][map.getColumns()+1];
		int width = 25;
		int height = 35;
		for(int i = 0; i < map.getRows(); i++) {
			GridPane rowLayout = new GridPane();
			rowLayout.setAlignment(Pos.CENTER);
			for(int j = 0; j <= map.getColumns(); j++) {
				arena[i][j] = new Button();
				arena[i][j].setBackground(darkBackground);
				arena[i][j].setOpacity(.6);
				arena[i][j].setBorder(new Border(new BorderStroke(gray, BorderStrokeStyle.SOLID, null, new BorderWidths(1, 1, 1, 1))));
				arena[i][j].setVisible(true);
				arena[i][j].setMinSize(width, height);
				arena[i][j].setOnAction(cellListener);
				rowLayout.add(arena[i][j], j, 0);
			}
			width += 2;
			arenaLayout.getChildren().add(rowLayout);
		}
        
        dockLayout = new FlowPane();
        Button perry = new Button();
        perry.setVisible(true);
        perry.setPrefSize(120, 120);
        perry.setBackground(createBackground("/assets/AgentP.png"));
        perry.setOnAction(selectWarriorListener);
        perry.setUserData(true);
        dockLayout.getChildren().add(perry);
        Button flea = new Button();
        flea.setVisible(true);
        flea.setPrefSize(120, 120);
        flea.setBackground(createBackground("/assets/TheFlea.png"));
        flea.setOnAction(selectWarriorListener);
        flea.setUserData(false);
        dockLayout.getChildren().add(flea);
        
		arenaLayout.setAlignment(Pos.CENTER);
		appLayout.setTop(statusLayout);
		appLayout.setCenter(arenaLayout);
		appLayout.setBottom(dockLayout);
		appStage.setScene(appScene);
		appStage.show();
	}
	
	private Background createBackground(String path) {
		return new Background(new BackgroundImage(new Image(getMediaFromPath(path)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(240, 240, false, false, true, false)));
	}
	
	private String getMediaFromPath(String path) {
		return getClass().getResource(path).toExternalForm();
	}
	
	private void resetCursorImage() {
		appScene.setCursor(Cursor.DEFAULT);
	}
	
	private void setCursorImage(Image img) {
		appScene.setCursor(new ImageCursor(img));
	}
	
	private void playMusic(String path) {
		if(mediaPlayer.getStatus().equals(Status.PLAYING)) {
			mediaPlayer.stop();
		}
		Media sound = new Media(getMediaFromPath(path));
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	EventHandler<ActionEvent> selectWarriorListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button warrior = (Button) event.getSource();
			setCursorImage(warrior.getBackground().getImages().get(0).getImage());
			playPerry = (boolean) warrior.getUserData();
		}
		
	};
	
	EventHandler<ActionEvent> cellListener = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button cell = (Button) event.getSource();
			// TODO: get the background from the cursor image
			resetCursorImage();
			if(playPerry) {
				playMusic("/assets/AgentP.mp3");
				cell.setBackground(createBackground("/assets/AgentP.png"));
			} else {
				playMusic("/assets/TheFlea.mp3");
				cell.setBackground(createBackground("/assets/TheFlea.png"));
			}
		}
		
	};
	
}

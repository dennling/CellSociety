package cellsociety_team12;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import cells.Cell;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;


public class Setup {
	
	private static final double SCREEN_HEIGHT = 400;
	private static final double SCREEN_WIDTH = 600;
	private static final Color DEFAULT_COLOR = Color.WHITE;
	public static final String FILE_EXTENSION = "*.xml";
	
	private Scene myScene;
	private Stage myStage;
	private SceneBuilder mySceneBuilder;
	private Game myGame;
	private Simulator mySimulator;
	private GameData myData;
	private FileChooser myChooser;

	public Setup(Stage stage){ 
		myStage = stage;
		double screenHeight = SCREEN_HEIGHT;
		double screenWidth = SCREEN_WIDTH;
		Color color = DEFAULT_COLOR;
		
		myChooser = makeChooser(FILE_EXTENSION);
		
		getData();
		
		String title = myData.getTitle();
		String author = myData.getAuthor();

		switch (myData.getGameType()){

			case "GameOfLife": myGame = new GameOfLife(myData);
			break;
			case "Segregation": myGame = new Segregation(myData);
			break;
			case "Fire": myGame = new Fire(myData);
			break;
			case "Wator": myGame = new Wator(myData);
			break;
		}
		
		mySceneBuilder = new SceneBuilder(title, author, screenHeight, screenWidth, color, myGame);
		myScene = mySceneBuilder.getScene();
		myStage.setScene(myScene);
		myStage.setTitle(title);
		myStage.show();
		myStage.setResizable(false);
		mySimulator = new Simulator(myGame, mySceneBuilder.getButtons(), myStage);
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	public Stage getStage(){
		return myStage;
	}
	
	private void getData() {
		File dataFile = myChooser.showOpenDialog(myStage);
		if (dataFile != null) {
			try {
				myData = new XMLParser().getData(dataFile);
			} catch (XMLException e) {
				Alert a = new Alert(AlertType.ERROR);
	            a.setContentText(String.format("ERROR reading file %s", dataFile.getPath()));
	            a.showAndWait();
			}
		}
	}
	
	public FileChooser makeChooser(String extension) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Data File");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extension));
		return chooser;		
	}
	
}


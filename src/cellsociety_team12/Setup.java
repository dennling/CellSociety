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
	
	private Scene myScene;
	private Stage myStage;
	private SceneBuilder mySceneBuilder;
	private Game myGame;
	private Simulator mySimulator;
	static final double HEIGHT_ADJUST_FACTOR = .625;
	static final double WIDTH_ADJUST_FACTOR = .9;
	private GameData myData;
	
	public static final String FILE_EXTENSION = "*.xml";
	private FileChooser myChooser = makeChooser(FILE_EXTENSION);
	
	
	public Setup(Stage stage){ //will need a file input normally
		// declare data object from XML Parser
		myStage = stage;
		getData();
		String title = "Wator";
		String author = "Advait";
		int screenHeight = 400;
		int screenWidth = 600;
		Color color = Color.WHITE;
		double simulationHeight = screenHeight * HEIGHT_ADJUST_FACTOR;
		double simulationWidth = screenWidth * WIDTH_ADJUST_FACTOR;

		switch (myData.getGameType()){
			//case "Wator": myGame = new watorGame();
			//break;
			case "GameOfLife": myGame = new GameOfLife(myData);
			break;
			case "Segregation": myGame = new Segregation(myData);
			break;
			//case "Fire": myGame = new fireGame();
			//break;
		}
		mySceneBuilder = new SceneBuilder(title, author, screenHeight, screenWidth, color, myGame);
		myScene = mySceneBuilder.getScene();
		stage.setScene(myScene);
		stage.setTitle(title);
		stage.show();
		stage.setResizable(false);
		mySimulator = new Simulator(myGame, mySceneBuilder.getButtons());
		
	}

	public Scene getScene() {
		return myScene;
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
	
	private FileChooser makeChooser(String extension) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Data File");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extension));
		return chooser;
		
	}
	
}

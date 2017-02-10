package cellsociety_team12;

import java.io.File;

import games.Fire;
import games.Game;
import games.GameOfLife;
import games.Segregation;
import games.Wator;
import graphs.FireGraph;
import graphs.GameOfLifeGraph;
import graphs.Graph;
import graphs.SegregationGraph;
import graphs.WatorGraph;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
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
	private Graph myGraph;
	private Simulator mySimulator;
	private GameData myData;
	private FileChooser myChooser;
	private static String STYLESHEET;

	public Setup(Stage stage){ 
		myStage = stage;
		
		myChooser = makeChooser(FILE_EXTENSION);
		
		getData();

		initializeGameGraphAndStyle();
		System.out.print(STYLESHEET);
		mySceneBuilder = new SceneBuilder(myData, myGame, myGraph, STYLESHEET);
		myScene = mySceneBuilder.getScene();
		myStage.setScene(myScene);
		myStage.setTitle(myData.getTitle());
		myStage.show();
		myStage.setResizable(false);
		mySimulator = new Simulator(myGame, mySceneBuilder.getButtons(), myStage, mySceneBuilder.getGraph());
	}

	private void initializeGameGraphAndStyle() {
		switch (myData.getGameType()){

			case "GameOfLife": 
				myGame = new GameOfLife(myData);
				myGraph = new GameOfLifeGraph();
				STYLESHEET = "resources/gameOfLife.css";
				break;
			case "Segregation": 
				myGame = new Segregation(myData);
				myGraph = new SegregationGraph();
				STYLESHEET = "resources/Segregation.css";
				break;
			case "Fire": 
				myGame = new Fire(myData);
				myGraph = new FireGraph();
				STYLESHEET = "resources/Fire.css";
				break;
			case "Wator":
				myGame = new Wator(myData);
				myGraph = new WatorGraph();
				STYLESHEET = "resources/Wator.css";
				break;
		}
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


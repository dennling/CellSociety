package cellsociety_team12;

import java.io.File;

import games.Ants;
import games.Fire;
import games.Game;
import games.GameOfLife;
import games.Segregation;
import games.Wator;
import graphs.AntGraph;
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
import scenes.HexagonDisplay;
import scenes.RectangleDisplay;
import scenes.SceneBuilder;
import scenes.TriangleDisplay;

/** 
 * The Setup class prompts the user for an input file and uses the data to select the
 * proper Game, Graph, and SceneBuilder subclass. It is responsible for initializing all
 * major program components.Therefore, creating an instance of this class is enough to 
 * start and run the cell automation simulation.
 * 
 * This class should be called whenever the user wants to setup a new program - it will
 * create a new GUI, Game, and Scene.
 * 
 * The Setup class solely takes a default stage as an input and therefore has no major
 * dependencies.
 *
 * @author advaitreddy
 *
 */

public class Setup {
	
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
		initializeSceneBuilder();
		
		myScene = mySceneBuilder.getScene();
		
		myStage.setScene(myScene);
		myStage.setTitle(myData.getTitle());
		myStage.show();
		myStage.setResizable(false);
		
		mySimulator = new Simulator(myGame, mySceneBuilder, myStage);
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
			case "Ants":
				myGame = new Ants(myData);
				myGraph = new AntGraph();
				STYLESHEET = "resources/Ant.css";
				break;
			default: 
				throw new XMLException("Not a valid Game Type", myData.getGameType());
		}
	}
	
	private void initializeSceneBuilder() {
		switch(myData.getCellShape()){
		case "rectangle":
			mySceneBuilder = new RectangleDisplay(myData, myGame, myGraph, STYLESHEET);
			break;
		case "triangle":
			mySceneBuilder = new TriangleDisplay(myData, myGame, myGraph, STYLESHEET);
			break;
		case "hexagon":
			mySceneBuilder = new HexagonDisplay(myData, myGame, myGraph, STYLESHEET);
			break;
		}
	}
	
}


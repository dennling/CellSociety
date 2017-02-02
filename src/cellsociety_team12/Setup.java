package cellsociety_team12;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Setup {
	
	private Scene myScene;
	private Stage myStage;
	private SceneBuilder mySceneBuilder;
	private Game myGame;
	private Simulator mySimulator;
	static final double HEIGHT_ADJUST_FACTOR = .625;
	static final double WIDTH_ADJUST_FACTOR = .9;
	
	public Setup(Stage stage){ //will need a file input normally
		// declare data object from XML Parser
		myStage = stage;
		String title = "Wator";
		String author = "Advait";
		int screenHeight = 400;
		int screenWidth = 600;
		Color color = Color.WHITE;
		double simulationHeight = screenHeight * HEIGHT_ADJUST_FACTOR;
		double simulationWidth = screenWidth * WIDTH_ADJUST_FACTOR;
		// need to implement these subclasses
//		String type = "Wator";
//		switch (type){
//			case "Wator": myGame = new watorGame();
//			break;
//			case "Game of Life": myGame = new gameOfLife();
//			break;
//			case "Segregation": myGame = new segregationGame();
//			break;
//			case "Fire": myGame = new fireGame();
//		}
		myGame = new Game(simulationWidth, simulationHeight); // this is assuming there is a new Game class
		mySceneBuilder = new SceneBuilder(title, author, screenHeight, screenWidth, color, myGame);
		myScene = mySceneBuilder.getScene();
		mySimulator = new Simulator(myGame, myScene);
		stage.setScene(myScene);
		stage.setTitle(title);
		stage.show();
		stage.setResizable(false);
		mySimulator = new Simulator(myGame, myScene);
		
	}
	
}

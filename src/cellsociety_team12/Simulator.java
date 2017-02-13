package cellsociety_team12;

import java.util.List;
import java.util.ResourceBundle;

import games.Game;
import graphs.Graph;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import scenes.SceneBuilder;

/** 
 * The Simulator class is responsible for maintaining the game loop. It creates both
 * the Timeline and the KeyFrame for the current game and handles all events related
 * to the buttons.
 * 
 * This class should be called whenever a user wants to start simulating a game.
 *
 * The Simulator depends on the current Game, SceneBuilder, and Stage.
 * 
 * @author advaitreddy
 *
 */

public class Simulator implements EventHandler<ActionEvent>{
	
	private static final double DEFAULT_FRAMES_PER_SECOND = 1;
	private static final int STEP_CYCLE_COUNT = 1;
	private static final int DEFAULT_RATE = 1;
	private static final double SPEED_UP_FACTOR = 2;
	private static final double SLOW_DOWN_FACTOR = .5;
	private static final String DEFAULT_RESOURCES = "resources/English";
	private double frames_per_second = DEFAULT_FRAMES_PER_SECOND;
	private ResourceBundle myResources;
	private Timeline animation;
	private Game myGame;
	private Graph myGraph;
	private Stage myStage;
	private List<Button> myButtonList;
	private Button stepButton;
	private Button speedButton;
	private Button slowButton;
	private Button loadButton;
	private Button playPauseButton;
	private Button resetButton;
	private double totalDuration;
	
	public Simulator(Game game, SceneBuilder mySceneBuilder, Stage stage){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
		myGame = game;
		myGraph = mySceneBuilder.getGraph();
		myStage = stage;
		myButtonList = mySceneBuilder.getButtons();
		totalDuration = 0;
		initializeButtons();

		KeyFrame frame = new KeyFrame(Duration.millis(1000 / frames_per_second),
                e -> {this.step(1.0 / frames_per_second);});
		
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public void step(double elapsedTime){
		myGame.updateGrid();
		myGame.getGrid().updateCellPopulationMap();
		totalDuration += animation.getCycleDuration().toMillis();
		myGraph.updateGraph(totalDuration, myGame.getGrid().getCellPopulationMap());
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == stepButton){
			stepThrough();
		}
		if (event.getSource() == speedButton){
			speedUp();
		}
		if (event.getSource() == slowButton){
			slowDown();
		}
		if (event.getSource() == loadButton){
			loadFile();
		}
		if (event.getSource() == playPauseButton){
			playOrPause();
		}
		if (event.getSource() == resetButton){
			reset();
		}
	}


	private void speedUp() {
		animation.setRate(animation.getRate() * SPEED_UP_FACTOR);
	}


	private void slowDown() {
		animation.setRate(animation.getRate() * SLOW_DOWN_FACTOR);
	}


	private void loadFile() {
		animation.stop();
		new Setup(myStage);
	}


	private void reset() {
		animation.stop();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.setRate(DEFAULT_RATE);
		myGraph.clear();
		totalDuration = 0;
		myGame.getGrid().resetGrid();
		animation.play();
	}


	private void playOrPause() {
		if (animation.getCycleCount() == STEP_CYCLE_COUNT){
			animation.setCycleCount(Timeline.INDEFINITE);
		}
		if (animation.getCurrentRate() == 0){
			animation.play();
			playPauseButton.setText(myResources.getString("PauseButtonText"));
		}
		else{
			animation.pause();
			playPauseButton.setText(myResources.getString("PlayButtonText"));
		}
	}


	public void stepThrough() {
		if (animation.getCycleCount() != STEP_CYCLE_COUNT){
			animation.getCuePoints().put("step point", Duration.millis(totalDuration));
			animation.stop();
			animation.setCycleCount(STEP_CYCLE_COUNT);
			playPauseButton.setText(myResources.getString("PlayButtonText"));
		}
		animation.playFrom("step point");
	}
	
	private void initializeButtons(){
		for (Button b : myButtonList){
			String buttonText = b.getText();
			if (buttonText == myResources.getString("StepButtonText")){
				stepButton = b;
			}
			else if (buttonText == myResources.getString("SpeedButtonText")){
				speedButton = b;
			}
			else if (buttonText == myResources.getString("SlowButtonText")){
				slowButton = b;
			}
			else if (buttonText == myResources.getString("ResetButtonText")){
				resetButton = b;
			}
			else if (buttonText == myResources.getString("PauseButtonText")){
				playPauseButton = b;
			}
			else if (buttonText == myResources.getString("LoadButtonText")){
				loadButton = b;
			}
			b.setOnAction(this);
		}
	}

}


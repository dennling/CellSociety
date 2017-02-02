package cellsociety_team12;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class Simulator {
	
	private Timeline animation;
	private Game myGame;
	private Scene myScene;
	
	public Simulator(Game game, Scene scene){
		myGame = game;
		myScene = scene;
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000/60),
                e -> {this.step(1/60);});
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
		
	}
	
	public void step(double elapsedTime){
		myGame.updateGrid();
		System.out.println("go");
	}
}

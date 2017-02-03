package cellsociety_team12;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Simulator implements EventHandler<ActionEvent>{
	
	private double frames_per_second = 60;
    private double millisecond_delay = 1000 / frames_per_second;
    private double second_delay = 1.0 / frames_per_second;
	private Timeline animation;
	private Game myGame;
	private List<Button> myButtonList;
	private Button stepButton;
	private Button speedButton;
	private Button slowButton;
	private Button loadButton;
	private Button playPauseButton;
	
	public Simulator(Game game, List<Button> buttonList){
		myGame = game;
		myButtonList = buttonList;
		initializeButtons();
		
		KeyFrame frame = new KeyFrame(Duration.millis(1000/60),
                e -> {this.step(1/60);});
		
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void step(double elapsedTime){
		myGame.updateGrid();
		System.out.println(FRAMES_PER_SECOND);
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == stepButton){
			
		}
		if (event.getSource() == speedButton){
			FRAMES_PER_SECOND = FRAMES_PER_SECOND * 2;
		}
		if (event.getSource() == slowButton){
			FRAMES_PER_SECOND = FRAMES_PER_SECOND * .5;
		}
		if (event.getSource() == loadButton){
			
		}
		if (event.getSource() == playPauseButton){
			if (animation.getCurrentRate() == 0){
				animation.play();
			}
			else{
				animation.pause();
			}
		}
		
	}
	
	private void initializeButtons(){
		for (Button b : myButtonList){
			switch(b.getText()){
				case "Step": stepButton = b;
				stepButton.setOnAction(this);
				break;
				case "Speed Up": speedButton = b;
				speedButton.setOnAction(this);
				break;
				case "Slow Down": slowButton = b;
				slowButton.setOnAction(this);
				break;
				case "Pause": playPauseButton = b;
				playPauseButton.setOnAction(this);
				break;
				case "Load File": loadButton = b;
				loadButton.setOnAction(this);
				break;
			}
		}
	}
	
	
}

package scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonBox {
	
	private static final double BUTTON_SIZE_FACTOR = 6;
	private static final double BUTTON_BOX_HEIGHT_FACTOR = .25;
	
	private ResourceBundle myResources;
	private VBox buttonBox;
	private List<Button> buttonList;
	private double buttonWidth;
	private double boxHeight;
	private Button stepButton;
	private Button speedButton;
	private Button slowButton;
	private Button loadButton;
	private Button playPauseButton;
	private Button resetButton;
	
	public ButtonBox(double height, double width, ResourceBundle resources){
		myResources = resources;
		boxHeight = height * BUTTON_BOX_HEIGHT_FACTOR;
		buttonBox = new VBox();
		buttonBox.setId("buttonBox");
		buttonBox.setMinHeight(boxHeight);
		buttonWidth = width/BUTTON_SIZE_FACTOR;
		setButtons();
	}
	
	/**
	 * Sets text and other parameters for each button and
	 * also stores each button in the global list of buttons
	 * 
	 * @param text - displayed text on button
	 * @return button with new parameters
	 */
	private Button makeButton(String text){
		Button button = new Button(text);
		buttonList.add(button);

		button.setMinWidth(buttonWidth);
		return button;
	}
	
	/**
	 * Makes the box on the bottom of the GUI that contains the buttons.
	 * Initializes all buttons and adds them to either the top or bottom
	 * row of the box. 
	 */
	private void setButtons() {
		HBox topRow = new HBox();
		topRow.setId("buttonBox");
		
		HBox bottomRow = new HBox();
		bottomRow.setId("buttonBox");
		
		buttonBox.getChildren().addAll(topRow, bottomRow);
		
		buttonList = new ArrayList<Button>();
		
		stepButton = makeButton(myResources.getString("StepButtonText"));
		speedButton = makeButton(myResources.getString("SpeedButtonText"));
		slowButton = makeButton(myResources.getString("SlowButtonText"));
		loadButton = makeButton(myResources.getString("LoadButtonText"));
		playPauseButton = makeButton(myResources.getString("PauseButtonText"));
		resetButton = makeButton(myResources.getString("ResetButtonText"));
		
		topRow.getChildren().addAll(playPauseButton, stepButton, loadButton, resetButton);
		bottomRow.getChildren().addAll(speedButton, slowButton);
	}
	
	public VBox getBox(){
		return buttonBox;
	}
	
	public List getList(){
		return buttonList;
	}
	
}

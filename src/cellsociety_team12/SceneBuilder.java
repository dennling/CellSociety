package cellsociety_team12;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SceneBuilder{
	
	private static final String DEFAULT_RESOURCES = "resources/English";
	private static final double SIMULATION_HEIGHT_FACTOR = .625;
	private static final double SIMULATION_WIDTH_FACTOR = .9;
	private static final double HEADER_HEIGHT_FACTOR = .125;
	private static final double BUTTON_BOX_HEIGHT_FACTOR = .25;
	private static final double SIDES_WIDTH_FACTOR = .05;
	private static final String FONT_TYPE = "futura";
	private static final double FONT_SIZE = 13;
	private static final int STANDARD_SPACING = 10;
	private static final Insets DEFAULT_INSETS = new Insets(STANDARD_SPACING, STANDARD_SPACING, STANDARD_SPACING, STANDARD_SPACING);
	private static final Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
	private ResourceBundle myResources;
	private BorderPane root;
	private String gameTitle;
	private String gameAuthor;
	private double screenHeight;
	private double screenWidth;
	private double simulationHeight;
	private double simulationWidth;
	private Color background;
	private Game myGame;
	private Scene myScene;
	private Button stepButton;
	private Button speedButton;
	private Button slowButton;
	private Button loadButton;
	private Button playPauseButton;
	private Button resetButton;
	private List<Button> buttonList;
	
	
	
	public SceneBuilder(String title, String author, double height, double width, Color color, Game game){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
		screenHeight = height;
		screenWidth = width;
		simulationHeight = screenHeight * SIMULATION_HEIGHT_FACTOR;
		simulationWidth = screenWidth * SIMULATION_WIDTH_FACTOR;
		background = color;
		myGame = game;
		gameTitle = title;
		gameAuthor = author;
		root = new BorderPane();
		myScene = new Scene(root, screenWidth, screenHeight, background);
		displayGrid(myGame.getGrid());
		createUI();
	}
	
	private void displayGrid(Grid grid){
		Group cells = new Group(); 
		root.setCenter(cells);
		for (int i=0; i<grid.getNumberOfRows(); i++){
			for (int j=0; j<grid.getNumberOfColumns(); j++){
				Rectangle shape = (Rectangle) grid.getCell(i, j).getShape();
				shape.setWidth((simulationWidth)/grid.getNumberOfRows());
				shape.setHeight((simulationHeight)/grid.getNumberOfColumns());
				shape.setX(i*shape.getWidth());
				shape.setY(j*shape.getHeight());
				cells.getChildren().add(shape);
			}
		}
	}
	
	private void createUI(){
		makeButtonBox();
		makeHeader();
		makeSides();
	}

	private void makeButtonBox() {
		VBox buttonBox = new VBox();
		setButtonBoxParameters(buttonBox);
		root.setBottom(buttonBox);
		
		HBox topRow = new HBox();
		setButtonRowParameters(topRow);
		
		HBox bottomRow = new HBox();
		setButtonRowParameters(bottomRow);
		
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
	
	private void setButtonBoxParameters(VBox buttonBox){
		buttonBox.setPadding(DEFAULT_INSETS);  
		buttonBox.setSpacing(STANDARD_SPACING);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setBackground(DEFAULT_BACKGROUND);
		buttonBox.setMinHeight(screenHeight * BUTTON_BOX_HEIGHT_FACTOR);
	}
	
	private void setButtonRowParameters(HBox buttonRow){
		buttonRow.setSpacing(STANDARD_SPACING);
		buttonRow.setAlignment(Pos.CENTER);
	}
	
	public Button makeButton(String text){
		Button button = new Button(text);
		buttonList.add(button);
		button.setMinWidth(100);
		button.setFont(Font.font(FONT_TYPE));
		return button;
	}
	
	public void setActionStepButton(EventHandler<ActionEvent> e){
		stepButton.setOnAction(e);
	}

	private void makeHeader() {
		VBox header = new VBox();
		header.setSpacing(1);
		root.setTop(header);
		Text titleText = new Text(gameTitle);
		titleText.setFont(Font.font(FONT_TYPE, FONT_SIZE));
		Text authorText = new Text(myResources.getString("AuthorPrecedingText") + gameAuthor);
		authorText.setFont(Font.font(FONT_TYPE, FONT_SIZE));
		header.getChildren().addAll(titleText, authorText);
		header.setAlignment(Pos.CENTER);
		header.setBackground(DEFAULT_BACKGROUND);
		header.setMinHeight(screenHeight * HEADER_HEIGHT_FACTOR);
	}

	private void makeSides() {
		VBox left = new VBox();
		VBox right = new VBox();
		left.setBackground(DEFAULT_BACKGROUND);
		right.setBackground(DEFAULT_BACKGROUND);
		root.setLeft(left);
		root.setRight(right);
		left.setMinWidth(screenWidth * SIDES_WIDTH_FACTOR);
		right.setMinWidth(screenWidth * SIDES_WIDTH_FACTOR);
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	public List<Button> getButtons(){
		return buttonList;
	}
	
}


package scenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import cellsociety_team12.GameData;
import games.Game;
import graphs.Graph;
import grids.Grid;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class SceneBuilder{
	
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
	private static final double SCREEN_HEIGHT = 400;
	private static final double GRAPH_HEIGHT = 200;
	private static final double GRAPH_WIDTH = 200;
	private static final double SCREEN_WIDTH = 600;
	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static String STYLESHEET;
	
	private ResourceBundle myResources;
	private VBox root;
	private BorderPane simulationWindow;
	private String gameTitle;
	private String gameAuthor;
	private double screenHeight;
	private double screenWidth;
	private double graphHeight;
	private double graphWidth;
	protected double simulationHeight;
	protected double simulationWidth;
	private Color background;
	private Game myGame;
	protected Grid myGrid;
	private Graph myGraph;
	private Scene myScene;
	private Button stepButton;
	private Button speedButton;
	private Button slowButton;
	private Button loadButton;
	private Button playPauseButton;
	private Button resetButton;
	private List<Button> buttonList;
	
	public SceneBuilder(GameData myData, Game game, Graph graph, String styleSheet){
		initializeInstanceVariables(myData, game, styleSheet, graph);
		displayGrid();
		createUI();
		createAndFillRoot();
		myScene = new Scene(root, screenWidth, screenHeight + graphHeight , background);
		myScene.getStylesheets().add(STYLESHEET);
	}

	private void createAndFillRoot() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(myGraph.getGraph(), simulationWindow);
	}

	private void setGraphParameters() {
		myGraph.getGraph().maxHeight(graphHeight);
		myGraph.getGraph().maxWidth(graphWidth);
	}

	private void initializeInstanceVariables(GameData myData, Game game, String styleSheet, Graph graph) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
		screenHeight = SCREEN_HEIGHT;
		screenWidth = SCREEN_WIDTH;
		graphHeight = GRAPH_HEIGHT;
		graphWidth = GRAPH_WIDTH;
		background = DEFAULT_COLOR;
		simulationHeight = screenHeight * SIMULATION_HEIGHT_FACTOR;
		simulationWidth = screenWidth * SIMULATION_WIDTH_FACTOR;
		simulationWindow = new BorderPane();
		myGraph = graph;
		setGraphParameters();
		myGame = game;
		myGrid = game.getGrid();
		gameTitle = myData.getTitle();
		gameAuthor = myData.getAuthor();
		STYLESHEET = styleSheet;
	}
	
	
	
	private void displayGrid(){
		Pane cells = new Pane(); 
		makeScrollable(cells);
		setGrid(cells);
	}

	private void makeScrollable(Pane cells) {
		ScrollPane sp = new ScrollPane();
		sp.setContent(cells);
		sp.setMinViewportHeight(simulationHeight);
		sp.setMinViewportWidth(simulationWidth);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.NEVER);
		simulationWindow.setCenter(sp);
	}
	
	protected abstract void setGrid(Pane cells);
	
	private void createUI(){
		makeButtonBox();
		makeHeader();
		makeSides();
	}

	private void makeButtonBox() {
		VBox buttonBox = new VBox();
		setButtonBoxParameters(buttonBox);
		simulationWindow.setBottom(buttonBox);
		
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

	private void makeHeader() {
		VBox header = new VBox();
		header.setSpacing(1);
		simulationWindow.setTop(header);
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
		simulationWindow.setLeft(left);
		simulationWindow.setRight(right);
		left.setMinWidth(screenWidth * SIDES_WIDTH_FACTOR);
		right.setMinWidth(screenWidth * SIDES_WIDTH_FACTOR);
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	public VBox getPane(){
		return root;
	}
	public List<Button> getButtons(){
		return buttonList;
	}
	
	public Graph getGraph(){
		return myGraph;
	}
	
}

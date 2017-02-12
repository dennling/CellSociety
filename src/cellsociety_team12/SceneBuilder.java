package cellsociety_team12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import games.Game;
import graphs.GameOfLifeGraph;
import graphs.Graph;
import grids.Grid;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
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
	private static final double SCREEN_HEIGHT = 400;
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
	private double simulationHeight;
	private double simulationWidth;
	private Color background;
	private Game myGame;
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
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
		screenHeight = SCREEN_HEIGHT; //move into SceneBuilder
		screenWidth = SCREEN_WIDTH;
		background = DEFAULT_COLOR;
		simulationHeight = screenHeight * SIMULATION_HEIGHT_FACTOR;
		simulationWidth = screenWidth * SIMULATION_WIDTH_FACTOR;
		myGame = game;
		gameTitle = myData.getTitle();
		gameAuthor = myData.getAuthor();
		STYLESHEET = styleSheet;
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		simulationWindow = new BorderPane();
		myScene = new Scene(root, screenWidth, screenHeight + 200, background);
		myScene.getStylesheets().add(STYLESHEET);
		displayGrid(myGame.getGrid());
		createUI();
		myGraph = graph;
		myGraph.getGraph().maxHeight(200);
		myGraph.getGraph().maxWidth(500);
		root.getChildren().addAll(myGraph.getGraph(), simulationWindow);
	}
	
	private void displayGrid(Grid grid){
		Pane cells = new Pane(); 
		ScrollPane sp = new ScrollPane();
		sp.setContent(cells);
		sp.setMinViewportHeight(simulationHeight);
		sp.setMinViewportWidth(simulationWidth);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.NEVER);
		simulationWindow.setCenter(sp);
		setHexagons(grid, cells);
		//setRectangles(grid, cells);
	}

	private void setRectangles(Grid grid, Pane cells) {
		for (int i=0; i<grid.getNumberOfRows(); i++){
			for (int j=0; j<grid.getNumberOfColumns(); j++){
				Rectangle shape = (Rectangle) grid.getCell(i, j).getShape();
				shape.setWidth(simulationWidth/(grid.getNumberOfRows()));
				shape.setHeight(simulationHeight/(grid.getNumberOfColumns()));
				shape.setX(i*shape.getWidth());
				shape.setY(j*shape.getHeight());
				cells.getChildren().add(shape);
			}
		}
	}

	private void setTriangles(Grid grid, Pane cells) {
		double xStart = 0;
		double yStart = 0;
		double sideLength = simulationWidth/(grid.getNumberOfRows());
		for (int i=0; i<grid.getNumberOfColumns(); i++){
			for (int j=0; j<grid.getNumberOfRows(); j++){
				Polygon triangle;
				if ((j+2) % 2 == 0){
					double[] coordinates = {xStart, yStart, xStart + sideLength, yStart, xStart, yStart + sideLength};
					triangle = new Polygon(coordinates);
					triangle.setFill(Color.PURPLE);
				}
				else{
					double[] coordinates = {xStart, yStart + sideLength, xStart + sideLength, yStart, xStart + sideLength, yStart + sideLength};
					triangle = new Polygon(coordinates);
					triangle.setFill(Color.YELLOW);
					xStart += sideLength;
				}
				triangle.setStroke(Color.WHITE);
				cells.getChildren().add(triangle);
				if (xStart >= simulationWidth){
					xStart = 0;
					yStart += sideLength;
				}
			}
		}
	}
	
	private void setHexagons(Grid grid, Pane cells){
		System.out.println(grid.getNumberOfRows());
		double sideLength = calculateSideLength(simulationWidth, grid);
		double spacer = sideLength/Math.sqrt(2);
		double xStart = spacer;
		double yStart = 0;
		boolean startedAtZero = true;
		int hexagonCount = 0;
		for (int i=0; i<grid.getNumberOfRows(); i++){
			for (int j=0; j<grid.getNumberOfColumns(); j++){
				hexagonCount++;
				Polygon hexagon = (Polygon) grid.getCell(i,j).getShape();
				Double[] coordinates = {xStart, yStart,xStart - spacer, yStart + spacer, xStart, yStart + 2*spacer, xStart + sideLength, yStart + 2*spacer, xStart + sideLength + spacer, yStart + spacer, xStart+sideLength, yStart};
				hexagon.getPoints().addAll(coordinates);// = Polygon(coordinates);
				//hexagon.setFill(Color.PURPLE);
				//hexagon.setStroke(Color.WHITE);
				cells.getChildren().add(hexagon);
				yStart += 2*spacer;
				if (hexagonCount == grid.getNumberOfRows()){
					if (startedAtZero){
						yStart = spacer;
						startedAtZero = false;
					}
					else{
						yStart = 0;
						startedAtZero = true;
					}
					hexagonCount = 0;
					xStart += (sideLength+spacer);
				}
			}
		}
	}
	
	private double calculateSideLength(double width, Grid grid){
		int sideLengthNum = grid.getNumberOfRows();
		int spacerNum = sideLengthNum + 1;
		double sideWidth =  width/((spacerNum/Math.sqrt(2))+sideLengthNum);
		return sideWidth;
	}
	
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


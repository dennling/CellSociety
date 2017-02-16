// This entire file is part of my masterpiece.
// Advait Reddy

package scenes;

import java.util.ResourceBundle;

import cellsociety_team12.GameData;
import games.Game;
import graphs.Graph;
import grids.Grid;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/** 
 * The purpose of the SceneBuilder class is to provide a framework through which
 * the GUI can be built. This class is what puts all the on-screen elements in
 * their correct places, connecting the button menu, graph, and simulation window.
 * 
 * I think this code is well-designed for a few reasons. First, it is clean
 * front-end code - there are many methods that partition the code so that it
 * is easily readable. Second, I have refactored my code so that any 
 * parameters that can be set using CSS have been removed from the Java and 
 * put there. This allows someone to change visual parts of the GUI without 
 * having to access the actual code. Third, I have segmented the parts of the
 * scene with the most inherent functionality - the graph and the buttons - 
 * into their own classes. This shortens the length of this class and allows
 * people to edit these parts of the project without messing with the main
 * GUI. Finally, this class has been made into an abstract superclass. The only
 * abstract method is setGrid. By doing this, I made my code extendible so that
 * different types of grid patterns can be implemented. See the TriangleDisplay
 * class for an example of a subclass.
 *
 * @author advaitreddy
 *
 */

public abstract class SceneBuilder{
	
	private static final String DEFAULT_RESOURCES = "resources/English";
	private static final double SIMULATION_HEIGHT_FACTOR = .625;
	private static final double SIMULATION_WIDTH_FACTOR = .9;
	private static final double HEADER_HEIGHT_FACTOR = .125;
	private static final double SIDES_WIDTH_FACTOR = .05;
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
	protected double simulationHeight;
	protected double simulationWidth;
	private Color background;
	protected Grid myGrid;
	private Graph myGraph;
	private ButtonBox myButtonBox;
	private Scene myScene;
	
	public SceneBuilder(GameData myData, Game game, Graph graph, String styleSheet){
		initializeInstanceVariables(myData, game, styleSheet, graph);
		displayGrid();
		createUI();
		root = new VBox();
		root.getChildren().addAll(myGraph.getGraph(), simulationWindow);
		myScene = new Scene(root, screenWidth, screenHeight + myGraph.getHeight() , background);
		myScene.getStylesheets().add(STYLESHEET);
	}

	/**
	 * Initializes all instance variables - this method was separated since
	 * there is a lot to initialize in this class.
	 * 
	 * @param myData - used to initialize game title and author
	 * @param game
	 * @param styleSheet
	 * @param graph
	 */
	private void initializeInstanceVariables(GameData myData, Game game, String styleSheet, Graph graph) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
		screenHeight = SCREEN_HEIGHT;
		screenWidth = SCREEN_WIDTH;
		background = DEFAULT_COLOR;
		simulationHeight = screenHeight * SIMULATION_HEIGHT_FACTOR;
		simulationWidth = screenWidth * SIMULATION_WIDTH_FACTOR;
		simulationWindow = new BorderPane();
		myGraph = graph;
		myGrid = game.getGrid();
		myButtonBox = new ButtonBox(screenHeight, screenWidth, myResources);
		gameTitle = myData.getTitle();
		gameAuthor = myData.getAuthor();
		STYLESHEET = styleSheet;
	}

	/**
	 * Initializes a pane to hold the cells, makes pane scrollable,
	 * and sets the Grid.
	 */
	private void displayGrid(){
		Pane cells = new Pane(); 
		makeScrollable(cells);
		setGrid(cells);
	}
	
	/**
	 * Sets all parameters to make the center pane scrollable.
	 * 
	 * @param cells - the pane that contains the scrollable content
	 */
	private void makeScrollable(Pane cells) {
		ScrollPane sp = new ScrollPane();
		sp.setContent(cells);
		sp.setMinViewportHeight(simulationHeight);
		sp.setMinViewportWidth(simulationWidth);
		simulationWindow.setCenter(sp);
	}
	
	/**
	 * This method is implemented differently depending on what
	 * type of shape the cells are. Different algorithms must be
	 * used to equally space triangles, rectangles, and hexagons.
	 * 
	 * @param cells - pane that holds all cells
	 */
	protected abstract void setGrid(Pane cells);
	
	/**
	 * Creates all parts of GUI except the simulation and graph.
	 * Puts the button box in place.
	 */
	private void createUI(){
		makeHeader();
		makeSides();
		simulationWindow.setBottom(myButtonBox.getBox());
	}

	/**
	 * Makes the header of the simulation window - displays the title and author
	 */
	private void makeHeader() {
		VBox header = new VBox();
		simulationWindow.setTop(header);
		Text titleText = new Text(gameTitle);
		Text authorText = new Text(myResources.getString("AuthorPrecedingText") + gameAuthor);
		header.getChildren().addAll(titleText, authorText);
		header.setId("header");
		header.setMinHeight(screenHeight * HEADER_HEIGHT_FACTOR);
	}

	/**
	 * Makes the sides of the simulation window - these currently display nothing
	 */
	private void makeSides() {
		VBox left = new VBox();
		VBox right = new VBox();
		left.setId("side");
		right.setId("side");
		simulationWindow.setLeft(left);
		simulationWindow.setRight(right);
		left.setMinWidth(screenWidth * SIDES_WIDTH_FACTOR);
		right.setMinWidth(screenWidth * SIDES_WIDTH_FACTOR);
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	public Graph getGraph(){
		return myGraph;
	}
	
	public ButtonBox getButtons(){
		return myButtonBox;
	}
	
}
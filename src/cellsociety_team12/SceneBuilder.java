package cellsociety_team12;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SceneBuilder {
	
	private BorderPane root;
	private String gameTitle;
	private String gameAuthor;
	private int screenHeight;
	private int screenWidth;
	private Color background;
	private Game myGame;
	private Scene myScene;
	
	public SceneBuilder(String title, String author, int height, int width, Color color, Game game){
		screenHeight = height;
		screenWidth = width;
		background = color;
		myGame = game;
		gameTitle = title;
		gameAuthor = author;
		root = new BorderPane();
		myScene = new Scene(root, screenWidth, screenHeight, background);
		displayGrid(myGame.getGrid());
		createUI();
	}
	
	private void displayGrid(Cell[][] grid){
		Group cells = new Group(); //center pane contains a group (make bigger) that contains the grid pane 
		root.setCenter(cells);
		for (int i=0; i<grid.length; i++){
			for (int j=0; j<grid.length; j++){
				cells.getChildren().add(grid[i][j].getShape());
			}
		}
	}
	
	private void createUI(){
		VBox buttons = new VBox();
		HBox row1 = new HBox();
		HBox row2 = new HBox();
		buttons.getChildren().addAll(row1, row2);
		root.setBottom(buttons);
		buttons.setPadding(new Insets(10, 10, 10, 10)); //make magic numbers constants 
		buttons.setSpacing(10);
		row1.setSpacing(10);
		row2.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);
		row1.setAlignment(Pos.CENTER);
		row2.setAlignment(Pos.CENTER);
		buttons.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Button stepButton = new Button("Step");
		Button speedButton = new Button("Speed Up");
		Button slowButton = new Button("Slow Down");
		Button loadButton = new Button("Load File");
		Button playPauseButton = new Button ("Pause");
		row1.getChildren().addAll(playPauseButton, stepButton, loadButton);
		row2.getChildren().addAll(speedButton, slowButton);
		VBox header = new VBox();
		header.setSpacing(1);
		root.setTop(header);
		Text titleText = new Text(gameTitle);
		titleText.setFont(Font.font("futura", 13));
		Text authorText = new Text("Credit to " + gameAuthor);
		authorText.setFont(Font.font("futura", 13));
		header.getChildren().addAll(titleText, authorText);
		header.setAlignment(Pos.CENTER);
		header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		header.setMinHeight(screenHeight/8);
		buttons.setMinHeight(screenHeight/4);
		VBox left = new VBox();
		VBox right = new VBox();
		left.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		right.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		root.setLeft(left);
		root.setRight(right);
		left.setMinWidth(screenWidth/20);
		right.setMinWidth(screenWidth/20);
	}
	
	private void createUI2(){
		HBox buttons = new HBox();
		root.setBottom(buttons);
		buttons.setPadding(new Insets(10, 10, 10, 10)); //make magic numbers constants 
		buttons.setSpacing(10);
		buttons.setAlignment(Pos.CENTER);
		buttons.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Button stepButton = new Button("Step");
		Button speedButton = new Button("Speed Up");
		Button slowButton = new Button("Slow Down");
		Button loadButton = new Button("Load File");
		Button playPauseButton = new Button ("Pause");
		buttons.getChildren().addAll(stepButton, speedButton, slowButton, loadButton, playPauseButton);
		VBox header = new VBox();
		header.setSpacing(1);
		root.setTop(header);
		Text titleText = new Text(gameTitle);
		titleText.setFont(Font.font("futura", 13));
		Text authorText = new Text("Credit to " + gameAuthor);
		authorText.setFont(Font.font("futura", 13));
		header.getChildren().addAll(titleText, authorText);
		header.setAlignment(Pos.CENTER);
		header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		header.setMinHeight(screenHeight/8);
		buttons.setMinHeight(screenHeight/8);
		VBox left = new VBox();
		VBox right = new VBox();
		left.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		right.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		root.setLeft(left);
		root.setRight(right);
		left.setMinWidth(screenWidth/20);
		right.setMinWidth(screenWidth/20);
	}
	
	public Scene getScene(){
		return myScene;
	}
	
}

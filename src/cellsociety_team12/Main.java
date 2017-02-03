package cellsociety_team12;

import java.io.File;
import java.util.Arrays;

import cells.Cell;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final String FILE_EXTENSION = "*.xml";
	
	private FileChooser myChooser = makeChooser(FILE_EXTENSION);
	
	@Override
	public void start(Stage s) throws Exception {
		//THE CODE THAT WILL ACTUALLY BE USED
		/*
		Setup setup = new Setup();
		s.setTitle(setup.retrieveTitle);
		Scene scene = setup.init();
		s.setScene(scene);
		s.show();*/
		
		//USED FOR CLASS TESTING
		
		/*
		Group r = new Group();
		s.setTitle("But");
		Scene scene = new Scene(r, 500, 500, Color.WHITE);
		Cell cell = new Cell(50, 50, "fake", new Rectangle());
		Rectangle shape = (Rectangle) cell.getShape();
		shape.setX(500/2);
		shape.setY(500/2);
		shape.setWidth(100);
		shape.setHeight(50);
		r.getChildren().add(shape);
		s.setScene(scene);
		s.show();
		*/
	
		GameOfLife game;
		File dataFile = myChooser.showOpenDialog(s);
		if (dataFile != null) {
			try {
				GameData data = new XMLParser().getData(dataFile);
				game = new GameOfLife(data);
				
				
				Group r = new Group();
				s.setTitle("But");
				Scene scene = new Scene(r, 500, 500, Color.WHITE);
				//game.updateGrid();
				Cell[][] grid = game.getGrid();
				for (int i = 0; i < grid.length; i++) {
					for (int k = 0; k < grid[0].length; k++){
						
						Cell cell = grid[i][k];
						Rectangle shape = (Rectangle) cell.getShape();
						shape.setWidth(500/100);
						shape.setHeight(500/100);
						shape.setX(0 + (i*shape.getWidth()));
						shape.setY(0 + (k*shape.getHeight()));
						r.getChildren().add(shape);
					}
				}
				s.setScene(scene);
				s.show();
				
				
				
				
			} catch (XMLException e) {
				Alert a = new Alert(AlertType.ERROR);
                a.setContentText(String.format("ERROR reading file %s", dataFile.getPath()));
                a.showAndWait();
			}
		}
		
		
	}
	
	private FileChooser makeChooser(String extension) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Data File");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extension));
		return chooser;
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}

}

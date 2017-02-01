package cellsociety_team12;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	@Override
	public void start(Stage s) throws Exception {
		//THE CODE THAT WILL ACTUALLY BE USED
		/*
		Setup setup = new Setup();
		s.setTitle(setup.retrieveTitle);
		Scene scene = setup.init();
		s.setScene(scene);
		s.show();*/
		
		
		
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
		
		
		
		
		//USED FOR CLASS TESTING
		
		Group r = new Group();
		s.setTitle("But");
		Scene scene = new Scene(r, 500, 500, Color.WHITE);
		GameOfLife game = new GameOfLife();
		game.updateGrid();
		Cell[][] grid = game.getGrid();
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[0].length; k++){
				
				Cell cell = grid[i][k];
				Rectangle shape = (Rectangle) cell.getShape();
				shape.setWidth(500/3);
				shape.setHeight(500/3);
				shape.setX(0 + (i*shape.getWidth()));
				shape.setY(0 + (k*shape.getHeight()));
				System.out.println(i);
				System.out.println(k);
				System.out.println(Arrays.toString(cell.getNeighbors()));
				r.getChildren().add(shape);
			}
		}
		s.setScene(scene);
		s.show();
		
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}

}

package cellsociety_team12;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
		
		
		
		
		
		
		
		
		
		//USED FOR CLASS TESTING
		/*
		Group r = new Group();
		s.setTitle("But");
		Scene scene = new Scene(r, 500, 500, Color.WHITE);
		Game game = new Game();
		game.updateGrid();
		Cell[][] grid = game.getGrid();
		for (int i = 0; i < grid.length; i++) {
			for (int k = 0; k < grid[0].length; k++){
				Cell cell = grid[i][k];
				cell.setWidth(500/5);
				cell.setHeight(500/5);
				cell.setX(0 + (i*cell.getWidth()));
				cell.setY(0 + (k*cell.getHeight()));
				System.out.println(i);
				System.out.println(k);
				System.out.println(Arrays.toString(cell.getNeighbors()));
				r.getChildren().add(cell);
			}
		}
		s.setScene(scene);
		s.show();*/
		
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}

}

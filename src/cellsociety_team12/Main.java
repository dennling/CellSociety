package cellsociety_team12;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage s) throws Exception {
		
		Setup startGame = new Setup(s);
	}

	public static void main(String[] args) {
		launch();
	}

}

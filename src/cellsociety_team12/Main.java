package cellsociety_team12;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage s) throws Exception {
		Setup startGame = new Setup(s);
	}

	public static void main(String[] args) {
		launch();
	}

}

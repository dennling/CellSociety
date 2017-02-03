package cellsociety_team12;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage s) throws Exception {
		
				s.setTitle("But");
				Scene scene = new Setup(s).getScene();
				s.setScene(scene);
				s.show();
				
				
	}

	public static void main(String[] args) {
		launch();
	}

}

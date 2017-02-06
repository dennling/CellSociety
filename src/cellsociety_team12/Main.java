package cellsociety_team12;

import java.io.File;

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

	public void start(Stage s) throws Exception {
		
		Setup startGame = new Setup(s);
	}



	public static void main(String[] args) {
		launch();
	}

}

package cellsociety_team12;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class XMLException extends RuntimeException{

	
	
	/**
	 * If there is an Exception, throw an Alert box, so user must update XML file to run 
	 * program
	 */
	private static final long serialVersionUID = 1L;


	public XMLException(String errorInformation, Throwable e) {
		this(e);
		Alert a = new Alert(AlertType.ERROR);
        a.setContentText(String.format("ERROR reading file: %s", errorInformation));
        a.showAndWait();

	}

	public XMLException(String information, Object values) {
		super(String.format(information, values));
		Alert a = new Alert(AlertType.ERROR);
        a.setContentText(String.format("ERROR reading file %s", information));
        a.showAndWait();
	}
	
	
	
	public XMLException(Throwable e) {
		super(e);
	}
	
	
}

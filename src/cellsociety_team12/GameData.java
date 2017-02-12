package cellsociety_team12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import org.w3c.dom.Element;

public class GameData {

	public static final String DATA_TYPE = "GameData";
	
	public static final List<String> DATA_FIELDS = Arrays.asList("gametype", "dimension",
			"initialPositions","prob","initialPositions2", "initialPositions3", "fishBreed",
			"sharkBreed","sharkStarve", "gameTitle","gameAuthor", "cellShape", "gridType",
			"cellType");

	private HashMap<String, String[]> myData; 
	
	
	public GameData(Map<String, String[]> data) {
		myData = (HashMap<String, String[]>) data;
	}
	
	
	public String getGameType() {
		return myData.get(DATA_FIELDS.get(0))[0];
	}
	
	public String getTitle() {
		return myData.get(DATA_FIELDS.get(9))[0];
	}
	
	public String getAuthor() {
		return myData.get(DATA_FIELDS.get(10))[0];
	}
	
	public int getDimensions() {
		return Integer.parseInt(myData.get(DATA_FIELDS.get(1))[0]);
	}
	
	public String[] getPositions1(){
		return myData.get(DATA_FIELDS.get(2));
	}
	
	public String[] getPositions2(){
		return myData.get(DATA_FIELDS.get(4));
	}
	
	public String[] getPositions3(){
		return myData.get(DATA_FIELDS.get(5));
	}
	
	public int[][] getInitialPositions(String[] stringPositions) {
		int[][] intPositions = new int[stringPositions.length][2];
		for (int i = 0; i < stringPositions.length; i++) {
			String[] sublist = stringPositions[i].split(" ");
			intPositions[i][0] = Integer.parseInt(sublist[0]);
			intPositions[i][1] = Integer.parseInt(sublist[1]);
			checkBounds(intPositions[i][0]);
			checkBounds(intPositions[i][1]);
		}	
		return intPositions;
	}
	
	private void checkBounds(int k) {
		if (k > getDimensions() || k < 0) {
			throw new XMLException("Initial starting point is out of bounds %s", k);
		}
	}
	
	public double getProb(){
		return Double.parseDouble(myData.get(DATA_FIELDS.get(3))[0]);
	}
	
	public double getFishBreed(){
		return Double.parseDouble(myData.get(DATA_FIELDS.get(6))[0]);
	}
	public double getSharkBreed(){
		return Double.parseDouble(myData.get(DATA_FIELDS.get(7))[0]);
	}
	public double getSharkStarve(){
		return Double.parseDouble(myData.get(DATA_FIELDS.get(8))[0]);
	}
	
	public String getCellShape() {
		try {
			return myData.get(DATA_FIELDS.get(11))[0];
		} catch (RuntimeException e) {
			throw new XMLException("NO CELL SHAPE", e);
		}
	}
	
	public String getGridType() {
		if (myData.get(DATA_FIELDS.get(12)).length == 0) {
			return "";
		}

		return myData.get(DATA_FIELDS.get(12))[0];
	}
	public String getCellType() {
		if (myData.get(DATA_FIELDS.get(13)).length == 0) {
			return "";
		}
		return myData.get(DATA_FIELDS.get(13))[0];
	}
	
}

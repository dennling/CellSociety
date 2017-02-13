package cellsociety_team12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all the data parsed from the XMLParser
 *
 */

public class GameData {

	public static final String DATA_TYPE = "GameData";
	
	public static final List<String> DATA_FIELDS = Arrays.asList("gametype", "dimension",
			"initialPositions","prob", "fishBreed", "sharkBreed","sharkStarve", "gameTitle",
			"gameAuthor", "cellShape", "gridType", "neighborType", "manualSize");

	private HashMap<String, String[]> myData; 
	
	
	public GameData(Map<String, String[]> data) {
		myData = (HashMap<String, String[]>) data;
	}
	
	public String getGameType() {
		return myData.get(DATA_FIELDS.get(0))[0];
		
	}
	
	public String getTitle() {
		return myData.get(DATA_FIELDS.get(7))[0];
	}
	
	public String getAuthor() {
		return myData.get(DATA_FIELDS.get(8))[0];
	}
	
	public int getDimensions() {
		int checkInt = Integer.parseInt(myData.get(DATA_FIELDS.get(1))[0]);
		if (checkInt <= 0) {
			throw new XMLException("WRONG DIMENSION SIZE", checkInt);
		}
		return checkInt;
	}
	
	/**
	 * Initial Positions have the structure : "x y type"
	 */
	public String[][] getInitialPositions() {
		String[] stringPositions = myData.get(DATA_FIELDS.get(2));
		String[][] positions = new String[stringPositions.length][2];
		for (int i = 0; i < stringPositions.length; i++) {
			String[] sublist = stringPositions[i].split(" ");
			positions[i] = sublist;
			checkBounds(Integer.parseInt(positions[i][0]));
			checkBounds(Integer.parseInt(positions[i][1]));
		}
		return positions;
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
		return Double.parseDouble(myData.get(DATA_FIELDS.get(4))[0]);
	}
	public double getSharkBreed(){
		return Double.parseDouble(myData.get(DATA_FIELDS.get(5))[0]);
	}
	public double getSharkStarve(){
		return Double.parseDouble(myData.get(DATA_FIELDS.get(6))[0]);
	}
	
	/**
	 * Essential for all XML files to have
	 */
	public String getCellShape() {
		try {
			return myData.get(DATA_FIELDS.get(9))[0];
		} catch (RuntimeException e) {
			throw new XMLException("NO CELL SHAPE", e);
		}
	}
	
	/**
	 * Grid type is toroidal or finite
	 */
	public String getGridType() {
		if (myData.get(DATA_FIELDS.get(10)).length == 0) {
			return "";
		}

		return myData.get(DATA_FIELDS.get(10))[0];
	}
	
	/**
	 * NeighborType is corners, edges, or normal
	 */
	public String getNeighborType() {
		if (myData.get(DATA_FIELDS.get(11)).length == 0) {
			return "";
		}
		return myData.get(DATA_FIELDS.get(11))[0];
	}
	
	/**
	 * If manual size exists, returns the size all shapes should be. If not, returns -1
	 */
	public int getManualSize() {
		if (myData.get(DATA_FIELDS.get(12)).length == 0) {
			return -1;
		}
		return Integer.parseInt(myData.get(DATA_FIELDS.get(12))[0]);
	}
	
}

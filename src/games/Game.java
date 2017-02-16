//This entire file is part of my masterpiece.
//Dennis Ling

/*
 * This class is the superclass for all of our simulators (games). It primarily handles the game logic, but also specifies the initial configuration of cells.
 * This code is well designed because it builds upon my learning of inheritance, especially as it pertains to superclasses and subclasses.
 * The connections that this class has with the rest of the program also shows how well designed the flow of the program is.
 * This code contains the necessary methods that the subclasses will either override or use exactly as specified.
 * This method also does not use public variables, but instead contains public methods to access these variables.
 * To fully appreciate the knowledge I've gained in doing this project, the other segment of my masterpiece (Fire) is an example of a well-coded subclass.
 */

package games;

import java.util.Random;
import java.util.Arrays;
import cells.Cell;
import cellsociety_team12.GameData;
import cellsociety_team12.XMLException;
import grids.Grid;

/*
 * Superclass for game. Primary role of game classes is to specify game logic and set initial 
 * configuration settings. 
 *
 */
public abstract class Game {
	
	private Grid myGrid;
	private String gameName;
	private GameData myData;
	
	public Game(GameData data) {
		myData = data;
		gameName = myData.getGameType();
		myGrid = createGrid(myData.getDimensions(), myData.getCellShape());
		setInitialPositions();
		myGrid.updateCellNeighbors();
	}
	
	/*
	 * Creates initial positions of significant cells based on XML input, if none exist, create default
	 * cells
	 */
	public void setInitialPositions() {	
		String[][] positions = myData.getInitialPositions();
		if (positions.length == 0) {
			setDefaultPositions(myData);
		} else {
			for (String[] each : positions) {
				if (each.length != 3) {
					throw new XMLException("Initial State not Given for Cell %s", Arrays.toString(each));
				}
				int x = Integer.parseInt(each[0]);
				int y = Integer.parseInt(each[1]);
				if (x < getGrid().getRow(0).length && x >= 0 && y < getGrid().getRow(0).length && y >= 0){ 
					getGrid().getCell(x,y).setType(each[2]);
				}	
			}
		}
	}
	
	protected abstract Grid createGrid(int dimensions, String cellShape);
	
	/*
	 * Determines how the cells react to their neighbors according to the rules of the game
	 */
	public abstract void gameLogic(Cell currentCell);
	
	/*
	 * If no initial parameters in XML, randomizes
	 */
	protected abstract void setDefaultPositions(GameData data);
	
	/*
	 * Generates a random placement on the grid 
	 */
	protected void randomCellGenerator(String type, GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType(type);
	}
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public void updateGrid() {
		myGrid.updateGrid();
	}

	public String getName(){
		return gameName;
	}
	
	public GameData getData() {
		return myData;
	}
	
}

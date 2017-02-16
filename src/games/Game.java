package games;

import java.util.Random;
import java.util.Arrays;
import cells.Cell;
import cellsociety_team12.GameData;
import cellsociety_team12.XMLException;
import grids.Grid;
/**
 * MASTERPIECE
 * hjt8
 * 
 * I think this code represents a good piece of code because it is a super class that is 
 * designed to be highly extendible. If any person wants to extend to a new game, they only
 * NEED to implement the abstract classes of this cell. In addition, I simplified the code to
 * where all repeated code among classes is implemented in superclass
 */

/**
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
	
	/**
	 * Creates initial positions of significant cells based on XML input, if non exist, create default
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
	
	/**
	 * Determines how the cells react to their neighbors according to the rules of the game
	 */
	public abstract void gameLogic(Cell currentCell);
	
	/**
	 * if no initial parameters in XML, randomizes
	 */
	protected abstract void setDefaultPositions(GameData data);
	
	/**
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

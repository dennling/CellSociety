package grids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cells.Cell;
import games.Game;

/**
 * Superclass for Grids. Controls everything from initialization to updating neighbor and cells. 
 * Also controls cellPopulationMap that is used to update the graph
 * 
 */
public abstract class Grid {

	private Cell[][] myGrid;
	private Game myGame;
	private Map<String, Integer> cellPopulationMap;
	
	public Grid(int dimensions, Game game, String cellShape){
		myGame = game;
		initializeGrid(dimensions, cellShape);
		cellPopulationMap = new HashMap<String,Integer>();
	}
	
	
	private void initializeGrid(int dimensions, String cellShape) {
		myGrid = new Cell[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int k = 0; k < dimensions; k++) {
				myGrid[i][k] = cellType(i, k, cellShape);
			}
		}
		updateCellNeighbors();
	}
	
	/**
	 * Update Grid each step in the simulation
	 */
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				myGame.gameLogic(currentCell);
			}
		}
		updateCellNeighbors();
	}
	
	/**
	 * update the neighbors for each cell
	 */
	
	public void updateCellNeighbors() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				currentCell.updateNeighbors(myGrid);
			}
		}
	}
	
	/**
	 * Abstract Class allows each game type grid to create cells based off of what they need
	 * 
	 */
	protected abstract Cell cellType(int x, int y, String cellShape);
	
	
	public void updateCellPopulationMap(){
		cellPopulationMap.clear();
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				String cellType = currentCell.getType();
				if (!cellPopulationMap.containsKey(cellType)){
					cellPopulationMap.put(cellType, 1);
				}
				else{
					int updatedPopulation = cellPopulationMap.get(cellType) + 1;
					cellPopulationMap.put(cellType, updatedPopulation);
				}
			}
		}
	}
	
	/**
	 * When user presses reset, resets the the game to the intial state
	 */
	public void resetGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				myGrid[i][k].setType(resetType());;
			}
		}
		myGame.setInitialPositions();
		updateCellNeighbors();
	}
	
	/**
	 * Allows classes to set all cells to original starting type on reset
	 */
	protected abstract String resetType();
	
	public Cell getCell(int x, int y) {
		return myGrid[x][y];
	}
	
	public Cell[] getRow(int x) {
		if (x >= myGrid[0].length || x < 0) {
			return null;
		}
		return myGrid[x];
	}
	
	public Cell[] getColumn(int y) {
		if (y >= myGrid.length || y < 0) {
			return null;
		}
		ArrayList<Cell> column = new ArrayList<Cell>();
		for (Cell[] each : myGrid) {
			column.add(each[y]);
		}
		return (Cell[]) column.toArray();
	}
	
	public int getNumberOfRows(){
		return myGrid.length;
	}
	
	public int getNumberOfColumns(){
		return myGrid[0].length;
	}
	
	public Cell[][] getGrid(){
		return myGrid;
	}
	
	public Game getGame(){
		return myGame;
	}
	
	public Map<String, Integer> getCellPopulationMap(){
		return cellPopulationMap;
	}

	/**
	 * When user presses a cell to change state, goes through grid, then grid calls Cell to 
	 * change the state -- in Fire, allows us to reset the neighbors
	 */
	public void setNeighborOnSwitch(Cell currentCell) {
		currentCell.switchType();
	}
	

}


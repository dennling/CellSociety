package grids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cells.Cell;
import games.Game;

public abstract class Grid {

	private Cell[][] myGrid;
	private Game myGame;
	private Map<String, Integer> cellPopulationMap;
	
	public Grid(int dimensions, Game game){
		initializeGrid(dimensions);
		myGame = game;
		cellPopulationMap = new HashMap<String,Integer>();
	}
	
	private void initializeGrid(int dimensions) {
		myGrid = new Cell[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int k = 0; k < dimensions; k++) {
				myGrid[i][k] = cellType(i, k);
			}
		}
		updateCellNeighbors();
	}
	
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				myGame.gameLogic(currentCell);
			}
		}
		updateCellNeighbors();
	}
	
	public void updateCellNeighbors() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				currentCell.updateNeighbors8(myGrid);
			}
		}
	}
	
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
	
	protected abstract Cell cellType(int x, int y);
	
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

	/* Way to remove repeated double for loop
	 
	private void iterateGrid(String method) {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				if (method.equals("updateGrid")) {
					Cell currentCell = myGrid[i][k];
					gameLogic(currentCell);
				} else if (method.equals("updateNeighbors")) {
					Cell currentCell = myGrid[i][k];
					currentCell.updateNeighbors(myGrid);
				} else {
					myGrid[i][k] = setCellType(i, k);
				}
			}
		}
	}
	*/

}


package cellsociety_team12;

import javafx.scene.shape.Rectangle;

import javax.xml.parsers.SAXParser;

public abstract class Game {
	
	private Cell[][] myGrid;
	
	
	public Game() {
		createGrid();
	}
	
	private void createGrid() {
		int gridSize = 3;
		myGrid = new Cell[gridSize][gridSize];
		for (int i = 0; i < gridSize; i++) {
			for (int k = 0; k < gridSize; k++) {
				myGrid[i][k] = setCellType(i, k);
			}
		}
		updateCellNeighbors();
	}
	
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				gameLogic(currentCell);
			}
		}
		updateCellNeighbors();
	}
	
	private void updateCellNeighbors() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				currentCell.updateNeighbors(myGrid);
			}
		}
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

	protected abstract void gameLogic(Cell currentCell);
	protected abstract Cell setCellType(int x, int y);
	
	public Cell[][] getGrid() {
		return myGrid;
	}
	
	
}

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
				myGrid[i][k] = new Cell(i, k, "normal", new Rectangle());
			}
		}
	}
	
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				if (currentCell.getNeighbors() == null) {
					currentCell.updateNeighbors(myGrid);
				}
				gameLogic(currentCell);
			}
		}
	}

	protected abstract void gameLogic(Cell currentCell);
	
	public Cell[][] getGrid() {
		return myGrid;
	}
	
	
}

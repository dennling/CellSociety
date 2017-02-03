package cellsociety_team12;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import cells.Cell;

public abstract class Grid {

	private Cell[][] myGrid;
	private Game myGame;
	private GridPane myGridPane;
	
	public Grid(int dimensions, Game game){
		myGridPane = new GridPane();
		initializeGrid(dimensions);
	}
	
	private void initializeGrid(int dimensions) {
		myGrid = new Cell[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int k = 0; k < dimensions; k++) {
				myGrid[i][k] = cellType(i, k);
				myGridPane.add(myGrid[i][k].getShape(), i, k);
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
	
	private void updateCellNeighbors() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				currentCell.updateNeighbors(myGrid);
			}
		}
	}
	
	private void initializeGridPane() {
		
	}
	
	protected abstract Cell cellType(int x, int y);
	
	public Cell getCell(int x, int y) {
		return myGrid[x][y];
	}
	
	public GridPane getGridPane() {
		return myGridPane;
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

}

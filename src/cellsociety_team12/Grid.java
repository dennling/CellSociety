package cellsociety_team12;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import cells.Cell;

public abstract class Grid {

	private Cell[][] myGrid;
	private Game myGame;
	
	public Grid(int dimensions, Game game, String cellShape){
		initializeGrid(dimensions, cellShape);
		myGame = game;
	}
	
	private void initializeGrid(int dimensions, String cellShape) {
		myGrid = new Cell[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int k = 0; k < dimensions; k++) {
				myGrid[i][k] = cellType(i, k, cellShape(cellShape));
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
	
	protected abstract Cell cellType(int x, int y, Shape cellShape);

	public Shape cellShape(String cellShape) {
		switch (cellShape) {
			case "rectangle": return new Rectangle();
			default: return new Polygon();
		}
	}
	
	
	
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


package cellsociety_team12;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.xml.parsers.SAXParser;

public class Game {
	
	private Cell[][] myGrid;
	
	public Game(double width, double height) {
		createGrid(width, height);
	}
	
	private void createGrid(double width, double height) {
		int gridSize = 9;
		double cellWidthSpacer = width/gridSize;
		double cellHeightSpacer = height/gridSize;
		double xLoc = 0;
		double yLoc = 0;
		myGrid = new Cell[gridSize][gridSize];
		for (int i = 0; i < gridSize; i++) {
			for (int k = 0; k < gridSize; k++) {
				Rectangle rect = new Rectangle(xLoc, yLoc, width/gridSize,height/gridSize);
				myGrid[i][k] = new Cell(i, k, "normal", rect);
				myGrid[i][k].getShape().setFill(Color.BLUE);
				myGrid[i][k].getShape().setStroke(Color.WHITE);
				yLoc += cellHeightSpacer;
				if (yLoc + .01 >= height){
					yLoc = 0;
					xLoc += cellWidthSpacer;
				}
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
				//gameLogic(currentCell);
			}
		}
	}

	//protected abstract void gameLogic(Cell currentCell);
	
	public Cell[][] getGrid() {
		return myGrid;
	}
	
	
}

package cellsociety_team12;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

public class Cell{
	
	private int myGridX;
	private int myGridY;
	private String myType;
	private Shape myShape;
	private Cell[] myNeighbors;

	
	public Cell(int x, int y, String type, Shape shape) {
		myGridX = x;
		myGridY = y;
		myType = type;
		myNeighbors = null;
		myShape = shape;
		setColor();
	}
	
	
	/**
	 * Used as a reference for updateNeighbors(Cell[][] grid). Allows for simplified code
	 * as the Cell's neighbors are updated. Checks all 8 possible neighbors
	 */
	private int[][] setPossibleNeighbors() {
		int[][] possibleNeighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
				{1, 0}, {1, -1}, {0, -1}};
		return possibleNeighbors;
	}
	
	public void updateNeighbors(Cell[][] grid) {
		myNeighbors = new Cell[8];
		int[][] possibleNeighbors = setPossibleNeighbors();
		int size = grid.length;
		for (int i = 0; i < myNeighbors.length; i++) {
			int currentX = myGridX + possibleNeighbors[i][0];
			int currentY = myGridY + possibleNeighbors[i][1];
			myNeighbors[i] = null;
			if (currentX >= 0 && currentX < size) {
				if (currentY >= 0 && currentY < size) {
					myNeighbors[i] = grid[currentX][currentY];
				}
			}	
		}
	}
	
	private void setColor() {
		if (myType.equals("normal")) {
			myShape.setFill(Color.BLUE);
		}
	}
	
	public String getType() {
		return myType;
	}
	
	public Cell[] getNeighbors() {
		return myNeighbors;
	}
	
	public Shape getShape() {
		return myShape;
	}
	
	
}

package cells;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

public abstract class Cell{
	
	private int myGridX;
	private int myGridY;
	private String myType;
	private Shape myShape;
	private Cell[] myNeighbors;
	
	public Cell(int x, int y, String type, Shape shape) {
		myGridX = x;
		myGridY = y;
		myType = type;
		myShape = shape;
		if (!type.equals("neighbor")){
			initiateNeighbors();
			setColor();
		}
	}
	
	private void initiateNeighbors() {
		myNeighbors = new Cell[8];
		for (int i = 0; i < myNeighbors.length; i++) {
			myNeighbors[i] = specifyNeighborCell();
		}
	}
	
	protected abstract Cell specifyNeighborCell();

	
	/**
	 * Because all cells have to update according to what they're previous state is, 
	 * Must create new cells and assign the same properties as neighboring cells
	 * @param grid
	 */
	
	public void updateNeighbors(Cell[][] grid) {
		int[][] possibleNeighbors = setPossibleNeighbors();
		int size = grid.length;
		for (int i = 0; i < myNeighbors.length; i++) {
			int currentX = myGridX + possibleNeighbors[i][0];
			int currentY = myGridY + possibleNeighbors[i][1];
			if (currentX >= 0 && currentX < size) {
				if (currentY >= 0 && currentY < size) {
					Cell currentNeighbor = myNeighbors[i];
					Cell currentGrid = grid[currentX][currentY];
					if (currentGrid != null) {
						currentNeighbor.setType(currentGrid.getType());
						currentNeighbor.setLocation(currentX, currentY);
					}
				}
			}	
		}
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
	
	protected abstract void setColor();
	
	public String getType() {
		return myType;
	}
	
	public Cell[] getNeighbors() {
		return myNeighbors;
	}
	
	public Shape getShape() {
		return myShape;
	}
	
	public void setType(String type) {
		myType = type;
		setColor(); 
	}
	
	public void setLocation(int x, int y){
		myGridX = x;
		myGridY = y;
	}
	
	
}

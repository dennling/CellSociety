package cells;

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

public abstract class Cell{
	
	private int myGridX;
	private int myGridY;
	private String myType;
	private Shape myShape;
	private String myShapeString;
	private NeighborManager myNeighbors;
	
	public Cell(int x, int y, String type, String shape, String gridType, String cellMode) {
		myGridX = x;
		myGridY = y;
		myType = type;
		myShapeString = shape;
		myShape = cellShape();
		if (!type.equals("neighbor")){
			myNeighbors = new NeighborManager(gridType, cellMode, this);
			setColor();
		}
	}
	
	public Shape cellShape() {
		switch (myShapeString) {
			case "rectangle": return new Rectangle();
			default: return new Polygon();
		}
	}
	
	public abstract Cell specifyNeighborCell();

	
	/**
	 * Because all cells have to update according to what they're previous state is, 
	 * Must create new cells and assign the same properties as neighboring cells
	 * @param grid
	 */
	/*
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
	}*/
	
	public void updateNeighbors(Cell[][] grid) {
		myNeighbors.updateNeighbors(grid);
	}


	/**
	 * Used as a reference for updateNeighbors(Cell[][] grid). Allows for simplified code
	 * as the Cell's neighbors are updated. Checks all 8 possible neighbors
	 */
	public abstract int[][] setPossibleNeighbors();
	
	protected abstract void setColor();
	
	public String getType() {
		return myType;
	}
	
	public Cell[] getNeighbors() {
		return myNeighbors.getNeighbors();
	}
	
	public Shape getShape() {
		return myShape;
	}
	
	public void setType(String type) {
		checkType(type);
		myType = type;
		setColor(); 
	}
	
	public abstract void checkType(String type);
	
	public abstract void switchType();
	
	public void setLocation(int x, int y){
		myGridX = x;
		myGridY = y;
	}
	
	public int getX(){
		return myGridX;
	}
	
	public int getY(){
		return myGridY;
	}
	
	public String getShapeString() {
		return myShapeString;
	}
	
	
}

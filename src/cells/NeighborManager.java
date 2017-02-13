package cells;

import java.util.Arrays;

public class NeighborManager {

	private Cell[] myNeighbors;
	private Cell myCell;
	private int[][] possibleNeighbors;
	private String myGridMode;
	
	public NeighborManager(String gridMode, String cellMode, Cell cell) {
		myCell = cell;
		myGridMode = gridMode;
		determineCellType(cellMode);
		initiateNeighbors();
		
	}
	
	private void determineCellType(String cellMode) {
		if (myCell.getShapeString().equals("rectangle")) {
			possibleNeighbors = checkModes(cellMode);
		} else if (myCell.getShapeString().equals("triangle")) {
			possibleNeighbors = setPossibleTriangleNeighbors();
		} else if (myCell.getShapeString().equals("hexagon")) {
			possibleNeighbors = setPossibleHexagonNeighbors();
		}
	}
		
	private int[][] checkModes(String cellMode) {
		int[][] possible;
		switch (cellMode) {
			case "corners":	possible = new int[][]{{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
			break;
			case "edges": possible = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
			break;
			default: possible = myCell.setPossibleNeighbors();
		}
		return possible;	
	}

	private int[][] setPossibleHexagonNeighbors() {
		int[][] possible;
		if (myCell.getX()%2 == 0){
			possible = new int[][]{{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {0, 1}, {-1, 0}};
		} else {
			possible = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
		}
		return possible;
	}

	private int[][] setPossibleTriangleNeighbors() {
		int[][] possible1 = new int[][]{{-1, 0}, {0, 1}, {1, 0}};
		int[][] possible2 = new int[][]{{-1, 0}, {0, -1}, {1, 0}};
		if (myCell.getX()%2 == myCell.getY()%2) {
			return possible1;
		} else {
			return possible2;
		}
	}
	
	private void initiateNeighbors() {
		myNeighbors = new Cell[possibleNeighbors.length];
		for (int i = 0; i < myNeighbors.length; i++) {
			myNeighbors[i] = myCell.specifyNeighborCell();
		}
	}
	
	public void updateNeighbors(Cell[][] grid) {
		int size = grid.length;
		for (int i = 0; i < myNeighbors.length; i++) {
			int currentX = myCell.getX() + possibleNeighbors[i][0];
			int currentY = myCell.getY() + possibleNeighbors[i][1];
			if (currentX >= 0 && currentX < size && currentY >= 0 && currentY < size) {
				updateNeighborCell(i, currentX, currentY, grid);
			} else if (myGridMode.equals("toroidal")) {
				adjustToroidal(i, currentX, currentY, grid);
			}
		}
	}
	
	private void updateNeighborCell(int index, int currentX, int currentY, Cell[][] grid ) {
		Cell currentNeighbor = myNeighbors[index];
		Cell currentGrid = grid[currentX][currentY];
		if (currentGrid != null) {
			currentNeighbor.setType(currentGrid.getType());
			currentNeighbor.setLocation(currentX, currentY);
		}
	}
	
	private void adjustToroidal(int index, int x, int y, Cell[][] grid) {
		int size = grid.length;
		if (x < 0) {
			x = size-1;
		} else if (x >= size){
			x = 0;
		}
		if (y < 0) {
			y = size -1;
		} else if (y >= size){
			y = 0;
		}
		updateNeighborCell(index, x, y, grid);
	}

	
	public Cell[] getNeighbors() {
		return myNeighbors;
	}
}

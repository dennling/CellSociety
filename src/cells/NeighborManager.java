package cells;

public class NeighborManager {

	private Cell[] myNeighbors;
	private Cell myCell;
	private int[][] possibleNeighbors;
	
	public NeighborManager(String gridMode, String cellMode, Cell cell) {
		myCell = cell;
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
		if (myCell.getY()%2 == 0){
			possible = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; 
		} else {
			possible = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 0}};
		}
		return possible;
	}
	
	/**
	 * ADJUST DEPENDING ON WHICH WAY WE START THE TRIANGLES
	 * @return
	 */
	private int[][] setPossibleTriangleNeighbors() {
		int[][] possible1 = new int[][]{{0, -1}, {-1, 0}, {0, 1}};
		int[][] possible2 = new int[][]{{0, -1}, {1, 0}, {0, 1}};
		return possible1;
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
	
	public Cell[] getNeighbors() {
		return myNeighbors;
	}
}

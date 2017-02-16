package cells;
/**
 * MASTERPIECE
 * hjt8
 * 
 * I think this is a good class because it reflects my implementation idea to separate the neighbors
 * from the general Cell class. It also demonstrates what I think is a clever way to find neighbors,
 * which was our vector method instead of looping through the entire grid to find the relevant 
 * cells. 
 */
/**
 * Class that controls the Neighbors of each Cell. PossibleNeighbors is a 2x2 grid that controls
 * where the cell looks in the grid for it's neighbors
 *
 */
public class NeighborManager {

	private Cell[] myNeighbors;
	private Cell myCell;
	private int[][] possibleNeighbors;
	private String myGridMode;
	
	/**
	 * 
	 * @param gridMode - finite vs. toroidal
	 * @param cellMode - corners, edges, normal
	 * @param cell - the owner of this neighbor
	 */
	public NeighborManager(String gridMode, String cellMode, Cell cell) {
		myCell = cell;
		myGridMode = gridMode;
		determineCellType(cellMode);
		initiateNeighbors();
		
	}
	/**
	 * Creates possibleNeighbors based on the original starting shape.
	 * Corners and Edges only apply to rectangles (hexagon and triangles don't have true corners
	 * or edges). 
	 * @param cellMode - corners or edges
	 */
	private void determineCellType(String cellMode) {
		if (myCell.getShapeString().equals("rectangle")) {
			possibleNeighbors = checkModes(cellMode);
		} else if (myCell.getShapeString().equals("triangle")) {
			possibleNeighbors = setPossibleTriangleNeighbors();
		} else if (myCell.getShapeString().equals("hexagon")) {
			possibleNeighbors = setPossibleHexagonNeighbors();
		}
	}
		
	/**
	 * checks to see if there is a special mode selected. If not, calls the cell's normal neighbors
	 * @param cellMode
	 */
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

	/**
	 * Triangle cells only have 3 neighbors
	 */
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
	

	/**
	 * Because all cells have to update according to what they're previous state is, 
	 * Must create new cells and assign the same properties as neighboring cells
	 * @param grid
	 */
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
	
	/**
	 * The actual code to update a specific cell in the neighbor array -- decreases dupiclate code
	 * @param index - index in neighbor array
	 * @param currentX - currentX of neighbor cell in grid
	 * @param currentY - currentY of neighbor cell in grid
	 * @param grid
	 */
	private void updateNeighborCell(int index, int currentX, int currentY, Cell[][] grid ) {
		Cell currentNeighbor = myNeighbors[index];
		Cell currentGrid = grid[currentX][currentY];
		if (currentGrid != null) {
			currentNeighbor.setType(currentGrid.getType());
			currentNeighbor.setLocation(currentX, currentY);
		}
	}
	
	/**
	 * if out of bounds, wrap around 
	 */
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

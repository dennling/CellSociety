package cells;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * MASTERPIECE
 * hjt8
 * 
 * I think this is a good class because, like Game, it is a simple class that provides a framework
 * for extension. The way this class is implemented is to create subclasses. It sets up what the 
 * new class needs. It also provides methods for returning necessary info. 
 */
/**
 * Superclass for cells, this class is responsible for maintaining everything pertaining to Cell.
 * Created another class for Neighbors, Neighbor manager, to actually manage the cell's current 
 * neighbors. Cell also contains the current shape of the cell that will be displayed on the UI.
 *
 */

public abstract class Cell{
	
	private int myGridX;
	private int myGridY;
	private String myType;
	private Shape myShape;
	private String myShapeString;
	private NeighborManager myNeighbors;
	
	/**
	 * 
	 * @param x - x location in grid
	 * @param y - y location in grid
	 * @param type - type of cell (i.e. alive, dead, fire, shark, etc.)
	 * @param shape - Shape of cell in String form
	 * @param gridType - finite vs toroidal
	 * @param cellMode - corners, edges, normal
	 */
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
	

	/**
	 * Sets the initial shape of a cell based off of settings in the xml file
	 */
	public Shape cellShape() {
		switch (myShapeString) {
			case "rectangle": return new Rectangle();
			default: return new Polygon();
		}
	}
	
	/**
	 * Initializes the neighbor cells to the correct type of cell according to what game it is
	 */
	public abstract Cell specifyNeighborCell();
	
	/**
	 * Loops through the cell's neighbors and updates the cell's neighbors based on current state in grid
	 */
	public void updateNeighbors(Cell[][] grid) {
		myNeighbors.updateNeighbors(grid);
	}


	/**
	 * Used as a reference for updateNeighbors(Cell[][] grid). Allows for simplified code
	 * as the Cell's neighbors are updated. Checks all possible neighbors. Although NeighborManager 
	 * handles cells now, some particular cells, such as fire, require a certain set of neighbors, unless
	 * a certain cell neighbor mode ("corners" or "edges") is specified
	 */
	public abstract int[][] setPossibleNeighbors();
	
	/**
	 * Allows each cell to specify their own color
	 */
	protected abstract void setColor();
	
	/**
	 * Type refers to states of cells "alive" or "fire", etc.
	 */
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
	
	/**
	 * Utilized by the UI to check and change the type of each cell on click
	 */
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

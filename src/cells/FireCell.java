package cells;

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;

/**
 * Cell for the Fire Game
 *
 */

public class FireCell extends Cell{

	public FireCell(int x, int y, String type, String shape, String gridType, String cellType) {
		super(x, y, type, shape, gridType, cellType);
	}

	@Override
	public Cell specifyNeighborCell() {
		return new FireCell(0, 0, "neighbor", getShapeString(), "", "");
	}

	@Override
	protected void setColor() {
		if (getType().equals("fire")) {
			getShape().setFill(Color.RED);
		} else if (getType().equals("tree")) {
			getShape().setFill(Color.GREEN);
		}
		else{
			getShape().setFill(Color.YELLOW);
		}
		getShape().setStroke(Color.BLACK);
	}

	/**
	 * According to the rules of fire, can only move in 4 directions
	 */
	@Override
	public int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, 0}, {0, 1}, 
			{1, 0}, {0, -1}};	
	return possibleNeighbors;
	}
	
	@Override
	public void checkType(String type) {
		if (!(type.equals("fire") || type.equals("tree") || type.equals("empty"))) {
			throw new XMLException("This is not a valid cell type for the chosen game %s", type);
		}
		return;
	}

	@Override
	public void switchType() {
		if (this.getType().equals("tree")){
			this.setType("fire");	
		}
		else if (this.getType().equals("empty")){
			this.setType("tree");
		}
		else {
			this.setType("empty");
		}
	}


}

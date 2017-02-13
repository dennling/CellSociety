package cells;

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;

/**
 * Cell for Segregation
 */

public class SegregationCell extends Cell{

	public SegregationCell(int x, int y, String type, String shape, String gridType, String cellType) {
		super(x, y, type, shape, gridType, cellType);
	}

	@Override
	public Cell specifyNeighborCell() {
		return new SegregationCell(0, 0, "neighbor", getShapeString(), "", "");
	}

	@Override
	protected void setColor() {
		if (getType().equals("one")) {
			getShape().setFill(Color.RED);
		} else if (getType().equals("two")) {
			getShape().setFill(Color.BLUE);
		}
		else{
			getShape().setFill(Color.WHITE);
		}
		getShape().setStroke(Color.BLACK);

	}

	@Override
	public int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
			{1, 0}, {1, -1}, {0, -1}};	
	return possibleNeighbors;
	}
	
	@Override
	public void checkType(String type) {
		if (!(type.equals("one") || type.equals("two") || type.equals("empty"))) {
			throw new XMLException("This is not a valid cell type for the chosen game %s", type);
		}
		return;
	}

	@Override
	public void switchType() {
		if (this.getType().equals("one")){
			this.setType("two");
		}
		else if (this.getType().equals("two")){
			this.setType("empty");
		}
		else {
			this.setType("one");
		}
	}
}

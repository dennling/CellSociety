package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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


}

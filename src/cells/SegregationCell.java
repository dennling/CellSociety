package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SegregationCell extends Cell{

	public SegregationCell(int x, int y, String type, Shape shape) {
		super(x, y, type, shape);
	}

	@Override
	protected Cell specifyNeighborCell() {
		return new SegregationCell(0, 0, "neighbor", new Rectangle());
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
	protected int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
			{1, 0}, {1, -1}, {0, -1}};	
	return possibleNeighbors;
	}


}

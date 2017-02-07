package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class FireCell extends Cell{

	public FireCell(int x, int y, String type, Shape shape) {
		super(x, y, type, shape);
	}

	@Override
	protected Cell specifyNeighborCell() {
		return new FireCell(0, 0, "neighbor", new Rectangle());
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

	@Override
	protected int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, 0}, {0, 1}, 
			{1, 0}, {0, -1}};	
	return possibleNeighbors;
	}


}

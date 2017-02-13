package cells;

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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


}

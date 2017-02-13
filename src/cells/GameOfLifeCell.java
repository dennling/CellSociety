package cells;
//AUTHOR: HENRY TAYLOR

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameOfLifeCell extends Cell{

	public GameOfLifeCell(int x, int y, String type, String shape, String gridType, String cellType) {
		super(x, y, type, shape, gridType, cellType);
	}

	@Override
	public Cell specifyNeighborCell() {
		return new GameOfLifeCell(0, 0, "neighbor", getShapeString(), "", "");
	}

	@Override
	protected void setColor() {
		if (getType().equals("dead")) {
			getShape().setFill(Color.TAN);
		} else if (getType().equals("alive")) {
			getShape().setFill(Color.BLUE);
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
		if (!(type.equals("alive") || type.equals("dead"))) {
			throw new XMLException("This is not a valid cell type for the chosen game %s", type);
		}
		return;
	}

	@Override
	public void switchType() {
		if (this.getType().equals("alive")){
			this.setType("dead");
		}
		else{
			this.setType("alive");
		}
		
	}


	
	

}

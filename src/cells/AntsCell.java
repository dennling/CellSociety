package cells;

import javafx.scene.shape.Shape;

public class AntsCell extends Cell{

	public AntsCell(int x, int y, String type, Shape shape) {
		super(x, y, type, shape);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Cell specifyNeighborCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int[][] setPossibleNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setColor() {
		// TODO Auto-generated method stub
		
	}
	
}
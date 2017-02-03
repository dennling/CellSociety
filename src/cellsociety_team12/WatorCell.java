package cellsociety_team12;

import cells.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WatorCell extends Cell{

	public WatorCell(int x, int y, String type, Shape shape) {
		super(x, y, type, shape);
	}

	@Override
	protected Cell specifyNeighborCell() {
		return new WatorCell(0, 0, "neighbor", new Rectangle());
	}

	@Override
	protected void setColor() {
		if (getType().equals("fish")) {
			getShape().setFill(Color.BLUE);
		} else if (getType().equals("shark")) {
			getShape().setFill(Color.ORANGE);
		}
		else{
			getShape().setFill(Color.BLACK);
		}
		
	}
}

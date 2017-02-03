package cells;
//AUTHOR: HENRY TAYLOR

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameOfLifeCell extends Cell{

	public GameOfLifeCell(int x, int y, String type, Shape shape) {
		super(x, y, type, shape);
	}

	@Override
	protected Cell specifyNeighborCell() {
		return new GameOfLifeCell(0, 0, "neighbor", new Rectangle());
	}

	@Override
	protected void setColor() {
		if (getType().equals("dead")) {
			getShape().setFill(Color.WHITE);
		} else if (getType().equals("alive")) {
			getShape().setFill(Color.BLACK);
		}
		
	}

}

package cellsociety_team12;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import cells.Cell;
import cells.GameOfLifeCell;

public class GameOfLifeGrid extends Grid {

	public GameOfLifeGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
	}

	@Override
	protected GameOfLifeCell cellType(int x, int y, Shape cellShape) {
		return new GameOfLifeCell(x, y, "dead", cellShape);
	}

}

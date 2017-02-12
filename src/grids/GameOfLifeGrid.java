package grids;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import cells.Cell;
import cells.GameOfLifeCell;
import games.Game;

public class GameOfLifeGrid extends Grid {

	public GameOfLifeGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
	}

	@Override
	protected GameOfLifeCell cellType(int x, int y, String shape) {
		return new GameOfLifeCell(x, y, "dead", shape, getGame().getData().getGridType(),getGame().getData().getCellType());
	}

}

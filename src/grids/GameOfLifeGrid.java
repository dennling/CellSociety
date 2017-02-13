package grids;

import cells.GameOfLifeCell;
import games.Game;

/**
 * Grid class for Game Of Life
 */
public class GameOfLifeGrid extends Grid {

	public GameOfLifeGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
	}

	@Override
	protected GameOfLifeCell cellType(int x, int y, String shape) {
		return new GameOfLifeCell(x, y, "dead", shape, getGame().getData().getGridType(), getGame().getData().getNeighborType());
	}

	@Override
	protected String resetType() {
		return "dead";
	}

}

package cellsociety_team12;

import javafx.scene.shape.Rectangle;
import cells.Cell;
import cells.GameOfLifeCell;

public class GameOfLifeGrid extends Grid {

	public GameOfLifeGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected GameOfLifeCell cellType(int x, int y) {
		return new GameOfLifeCell(x, y, "dead", new Rectangle());
	}
	
}

package cellsociety_team12;

import javafx.scene.shape.Rectangle;
import cells.GameOfLifeCell;

public class FireGrid extends Grid {

	public FireGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected GameOfLifeCell cellType(int x, int y) {
		return new GameOfLifeCell(x, y, "tree", new Rectangle());
	}
	
}

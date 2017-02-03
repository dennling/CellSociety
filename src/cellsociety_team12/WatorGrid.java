package cellsociety_team12;

import javafx.scene.shape.Rectangle;
import cells.GameOfLifeCell;

public class WatorGrid extends Grid {

	public WatorGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected GameOfLifeCell cellType(int x, int y) {
		return new GameOfLifeCell(x, y, "empty", new Rectangle());
	}
	
}

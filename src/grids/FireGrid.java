package grids;

import javafx.scene.shape.Rectangle;
import cells.FireCell;
import games.Game;

public class FireGrid extends Grid {

	public FireGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected FireCell cellType(int x, int y) {
		return new FireCell(x, y, "tree", new Rectangle());
	}
	
}

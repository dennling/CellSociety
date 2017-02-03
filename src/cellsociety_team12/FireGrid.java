package cellsociety_team12;

import javafx.scene.shape.Rectangle;
import cells.FireCell;

public class FireGrid extends Grid {

	public FireGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected FireCell cellType(int x, int y) {
		return new FireCell(x, y, "tree", new Rectangle());
	}
	
}

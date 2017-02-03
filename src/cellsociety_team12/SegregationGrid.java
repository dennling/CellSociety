package cellsociety_team12;

import cells.SegregationCell;
import javafx.scene.shape.Rectangle;

public class SegregationGrid extends Grid {

	public SegregationGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected SegregationCell cellType(int x, int y) {
		return new SegregationCell(x, y, "empty", new Rectangle());
	}
	
}

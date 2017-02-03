package cellsociety_team12;

import javafx.scene.shape.Rectangle;
import cells.WatorCell;

public class WatorGrid extends Grid {

	public WatorGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected WatorCell cellType(int x, int y) {
		return new WatorCell(x, y, "empty", new Rectangle(), 0, 0);
	}
	
}

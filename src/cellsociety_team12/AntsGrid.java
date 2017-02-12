package cellsociety_team12;
import java.util.ArrayList;

import cells.AntsCell;
import cells.Cell;
import cells.WatorCell;
import javafx.scene.shape.Rectangle;

public class AntsGrid extends Grid{

	public AntsGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected Cell cellType(int x, int y) {
		return new AntsCell(x, y, "ground", new Rectangle(), new ArrayList<Boolean>(), new ArrayList<Boolean>(), new int[4]);
	}
	
}
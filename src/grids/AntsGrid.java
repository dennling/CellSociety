package grids;
import java.util.ArrayList;

import cells.AntsCell;
import cells.Cell;
import cells.WatorCell;
import games.AntObject;
import games.Game;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AntsGrid extends Grid{

	public AntsGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
	}

	@Override
	protected AntsCell cellType(int x, int y, String cellShape) {
		return new AntsCell(x, y, "ground", cellShape, getGame().getData().getGridType(), getGame().getData().getNeighborType(), new ArrayList<AntObject>(), new ArrayList<AntObject>(), new int[4]);
	}



}
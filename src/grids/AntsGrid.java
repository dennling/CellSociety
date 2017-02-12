package grids;
import java.util.ArrayList;

import cells.AntsCell;
import cells.Cell;
import cells.WatorCell;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AntsGrid extends Grid{

	public AntsGrid(int dimensions, Game game) {
		super(dimensions, game);
	}

	@Override
	protected Cell cellType(int x, int y, Shape cellShape) {
		return new AntsCell(x, y, "ground", cellShape, new ArrayList<Boolean>(), new ArrayList<Boolean>(), new int[4]);
	}



}
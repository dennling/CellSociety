package grids;

import cells.Cell;
import cells.FireCell;
import games.Game;

public class FireGrid extends Grid {

	public FireGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
	}

	@Override
	protected FireCell cellType(int x, int y, String shape) {
		return new FireCell(x, y, "tree", shape, getGame().getData().getGridType(), getGame().getData().getNeighborType());
	}

	@Override
	protected String resetType() {
		return "tree";
	}
	
	
	/**
	 * If user clicks on Fire, changes the state for the neighbors
	 */
	@Override
	public void setNeighborOnSwitch(Cell currentCell) {
		currentCell.switchType();
		Cell[] neighbors = currentCell.getNeighbors();
		for (Cell each: neighbors) {
			Cell currNeighbor = getCell(each.getX(), each.getY());
			for (Cell eachNeighbor: currNeighbor.getNeighbors()) {
				if (eachNeighbor.getX() == currentCell.getX() && eachNeighbor.getY() == currentCell.getY()) {
					eachNeighbor.setType(currentCell.getType());
				}
			}
		}
	}
	
	
}

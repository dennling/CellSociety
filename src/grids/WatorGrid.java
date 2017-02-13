package grids;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import cells.Cell;
import cells.WatorCell;
import games.Game;

public class WatorGrid extends Grid {

	public WatorGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
	}

	@Override
	protected WatorCell cellType(int x, int y, String cellShape) {
		return new WatorCell(x, y, "empty", cellShape, getGame().getData().getGridType(),
				getGame().getData().getNeighborType(), 0, 0, "empty");
	}

	@Override
	public void updateGrid(){
		updateSpecific("shark");
		updateSpecific("fish");
		updateSpecific("all");
		updateCellNeighbors();
	}

	private void updateSpecific(String type){
		for (int i = 0; i < getGrid().length; i++) {
			for (int k = 0; k < getGrid().length; k++) {
				WatorCell currentCell = (WatorCell)getGrid()[i][k];
				if(currentCell.getType().equals(type)) getGame().gameLogic(currentCell);
				if("all".equals(type)){
					currentCell.setType(currentCell.getFutureType());
					currentCell.setFutureType("empty");
				}
			}
		}
	}

	@Override
	protected String resetType() {
		return "empty";
	}


}

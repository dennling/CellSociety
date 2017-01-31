package cellsociety_team12;

import javafx.scene.shape.Rectangle;

public class GameOfLife extends Game{

	public GameOfLife() {
		super();
	}
	
	
	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		
		
	}
	
	@Override
	protected Cell setCellType(int x, int y) {
		return new Cell(x, y, "dead", new Rectangle());
	}

}

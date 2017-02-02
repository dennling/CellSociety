package cellsociety_team12;

import cells.Cell;
import javafx.scene.shape.Rectangle;

public class GameOfLife extends Game{

	public GameOfLife(GameData data) {
		super(data);
	}
	
	
	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		int liveCount = 0;
		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i].getType().equals("alive")){
				liveCount += 1;
			}
		}
		if (liveCount == 3) {
			currentCell.changeType("alive");
		} else if (liveCount == 2) {
			currentCell.changeType("alive");
		} else {
			currentCell.changeType("dead");
		}
	}
	
	@Override
	protected Cell setCellType(int x, int y) {
		return new Cell(x, y, "dead", new Rectangle());
	}

}

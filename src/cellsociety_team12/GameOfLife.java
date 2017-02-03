package cellsociety_team12;

import cells.Cell;
import cells.GameOfLifeCell;
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
			currentCell.setType("alive");
		} else if (liveCount == 2) {
			currentCell.setType("alive");
		} else {
			currentCell.setType("dead");
		}
	}
	
	@Override
	protected GameOfLifeCell setCellType(int x, int y) {
		return new GameOfLifeCell(x, y, "dead", new Rectangle());
	}

	@Override
	protected void setInitialPositions(GameData data) {
		if (data.getInitialPositions().length == 0) {
			setDefaultPositions();
		} else {
			int[][] positions = data.getInitialPositions();
			for (int[] each : positions) {
				getGrid()[each[0]][each[1]].setType("alive");
			}
		}
		
	}
	
	protected void setDefaultPositions() {
		
	}

}

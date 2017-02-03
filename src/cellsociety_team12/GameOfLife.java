package cellsociety_team12;
//AUTHOR: HENRY TAYLOR


import java.util.Random;

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
	protected void setInitialPositions(GameData data) {
		if (data.getInitialPositions().length == 0) {
			setDefaultPositions(data);
		} else {
			int[][] positions = data.getInitialPositions();
			for (int[] each : positions) {
				getGrid().getCell(each[0],each[1]).setType("alive");
			}
		}
		
	}
	
	@Override
	protected Grid createGrid(int dimensions) {
		return new GameOfLifeGrid(dimensions, this);
	}
	
	@Override
	protected void setDefaultPositions(GameData data) {
		for(int i =0; i < data.getDimensions()/2; i++) {
			Random numberGenerator = new Random();
			int randomX = numberGenerator.nextInt(data.getDimensions());
			int randomY = numberGenerator.nextInt(data.getDimensions());
			getGrid().getCell(randomX, randomY).setType("alive");
		}
	}

}

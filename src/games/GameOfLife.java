package games;
//AUTHOR: HENRY TAYLOR


import java.util.Arrays;
import java.util.Random;

import cells.Cell;
import cells.GameOfLifeCell;
import cellsociety_team12.GameData;
import grids.GameOfLifeGrid;
import grids.Grid;
import javafx.scene.shape.Rectangle;

public class GameOfLife extends Game{

	public GameOfLife(GameData data) {
		super(data);
	}
	
	@Override
	public void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		int liveCount = 0;
		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i].getType().equals("alive")){
				liveCount += 1;
			}
		}
		if (currentCell.getType().equals("dead")) {
			if (liveCount == 3) {
				currentCell.setType("alive");
			}
		} else {
			if (liveCount > 3 || liveCount < 2) {
				currentCell.setType("dead");
			}
		}
	}
	
	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new GameOfLifeGrid(dimensions, this, cellShape);
	}
	
	@Override
	protected void setDefaultPositions(GameData data) {
		for(int i =0; i < (data.getDimensions()*data.getDimensions())/2; i++) {
			randomCellGenerator("alive", data);
		}
	}

}

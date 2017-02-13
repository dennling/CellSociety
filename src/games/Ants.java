package games;

import java.util.ArrayList;
import java.util.Random;

import cells.AntsCell;
import cells.Cell;
import cellsociety_team12.GameData;
import grids.AntsGrid;
import grids.Grid;

public class Ants extends Game{


	public Ants(GameData data) {
		super(data);
		
		//get data for ant starting positions, nest, and food source
	}

	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new AntsGrid(dimensions, this, cellShape);
	}

	@Override
	public void gameLogic(Cell currentCell) {
		AntsCell curr = (AntsCell)currentCell;
		for(AntObject ant: curr.getAnts(true)){
			antForage(ant, curr);
		}
	}
	
	private void antForage(AntObject ant, AntsCell curr){ //check if has food, return to nest or look for food
		if(ant.hasFood()) ant.returnNest(curr);
		else ant.findFood(curr);
	}


	@Override
	protected void setDefaultPositions(GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType("ground");
	}
}
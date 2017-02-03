package cellsociety_team12;

import java.util.Random;

import cells.Cell;

public class Fire extends Game{
	public Fire(GameData data) {
		super(data);
	}

	private Grid myGrid = getGrid();
	private double fireChance = 0.5;

	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();//neighbors does not include diagonals
		
		if(currentCell.getType().equals("fire")){
			for(int i=0; i<neighbors.length; i++){ 
				if(neighbors[i].getType().equals("tree")){
					double roll = Math.random(); 
					if(roll<=fireChance){
						neighbors[i].setType("fire"); //set neighbor to be on fire
					}
				}
			}
			currentCell.setType("empty");
		}
	}
	
	@Override
	protected void setInitialPositions(GameData data) {
		if (data.getInitialPositions().length == 0) {
			setDefaultPositions(data);
		} else {
			int[][] positions = data.getInitialPositions();
			for (int[] each : positions) {
				getGrid().getCell(each[0],each[1]).setType("fire");
			}
		}
		
	}
	
	@Override
	protected Grid createGrid(int dimensions) {
		return new GameOfLifeGrid(dimensions, this);
	}
	
	@Override
	protected void setDefaultPositions(GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType("fire");
	}

}
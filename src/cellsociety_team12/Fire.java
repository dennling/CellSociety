package cellsociety_team12;

import java.util.Random;

import cells.Cell;

public class Fire extends Game{


	private Grid myGrid = getGrid();
	private Cell[][] grid = myGrid.getGrid();
	private double fireChance;

	public Fire(GameData data) {
		super(data);
		fireChance = data.getProb();
	}

	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();//neighbors does not include diagonals

		if(currentCell.getType().equals("tree")){
			for(int i=0; i<neighbors.length; i++){ 
				if(neighbors[i].getType().equals("fire")){
					double roll = Math.random(); 
					if(roll<=fireChance){
						currentCell.setType("fire"); //set neighbor to be on fire
					}
				}
			}
		}
		else if(currentCell.getType().equals("fire")){
			currentCell.setType("empty");
		}
	}



	@Override
	protected Grid createGrid(int dimensions) {
		return new FireGrid(dimensions, this);
	}

	@Override
	protected void setDefaultPositions(GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType("fire");
	}


	@Override
	protected String setInitialCellType(int type) {
		if(type == 0) return "fire";
		else return "tree";
	}

}
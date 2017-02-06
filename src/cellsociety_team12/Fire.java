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
		System.out.println(fireChance+"");
		System.out.println(neighbors.length+"");
		if(currentCell.getType().equals("fire")){
			for(int i=0; i<neighbors.length; i++){ 
				Cell checkSet = grid[neighbors[i].getX()][neighbors[i].getY()];
				if(neighbors[i].getType().equals("tree")){
					double roll = Math.random(); 
					if(roll<=fireChance){
						checkSet.setType("fire"); //set neighbor to be on fire
					}
				}
			}
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
package games;

import cells.Cell;
import cellsociety_team12.GameData;
import grids.FireGrid;
import grids.Grid;

/**
 * Class for the Fire Game
 *
 */
public class Fire extends Game{

	private double fireChance;

	public Fire(GameData data) {
		super(data);
		fireChance = data.getProb();
	}

	@Override
	public void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();

		if(currentCell.getType().equals("tree")){
			for(int i=0; i<neighbors.length; i++){ 
				if(neighbors[i].getType().equals("fire")){
					double roll = Math.random(); 
					if(roll<=fireChance){
						currentCell.setType("fire"); 
					}
				}
			}
		}
		else if(currentCell.getType().equals("fire")){
			currentCell.setType("empty");
		}
	}
	
	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new FireGrid(dimensions, this, cellShape);
	}

	@Override
	protected void setDefaultPositions(GameData data) {
		randomCellGenerator("fire", data);
	}
}
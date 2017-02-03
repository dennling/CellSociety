package cellsociety_team12;

import cells.Cell;

public class Segregation extends Game{
	public Segregation(GameData data) {
		super(data);
	}

	private Grid myGrid = getGrid();
	private Cell[][] grid = myGrid.getGrid();
	private double propThreshold = 0.5;
	private double countOne;
	private double countTwo;
	private double prop;
	
	private void initialize(){
		countOne = 0;
		countTwo = 0;
		prop = 0;
	}
	
	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		initialize();

		countNeighbors(neighbors);
		calcProp(currentCell);
		
		if(prop<propThreshold){ //if cell is dissatisfied, move to empty space. pick first empty from top.
			for(int i=0; i<grid.length; i++){
				for(int j=0; j<grid.length; j++){
					if(grid[i][j].getType().equals("empty")) {
						grid[i][j].setType(currentCell.getType());
						currentCell.setType("empty");
						break;
					}
				}
			}
		}
			
	}
	
	private void countNeighbors(Cell[] neighbors){
		for(Cell iter: neighbors){ //iterate through neighbors, counting how many neighbors of each type
			if(iter.getType().equals("one")) countOne++;
			else if(iter.getType().equals("two")) countTwo++;
		}
	}
	
	private void calcProp(Cell currentCell){
		if(currentCell.getType().equals("one")) prop = countOne/(countTwo+countOne); //calc proportion of common neighbors
		else prop = countTwo/(countTwo+countOne);
	}

	@Override
	protected Grid createGrid(int dimensions) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void setInitialPositions(GameData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDefaultPositions(GameData data) {
		// TODO Auto-generated method stub
		
	}
	
	

}
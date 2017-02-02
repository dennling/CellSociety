package cellsociety_team12;

public class Segregation extends Game{
	private Cell[][] myGrid = getGrid();
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
			for(int i=0; i<myGrid.length; i++){
				for(int j=0; j<myGrid.length; j++){
					if(myGrid[i][j].getType().equals("empty")) {
						//currentCell.setX(i);
						//currentCell.setY(j);
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
	
	

}
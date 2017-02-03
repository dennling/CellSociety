 package cellsociety_team12;

import cells.Cell;

public class Wator extends Game{
	public Wator(GameData data) {
		super(data);
	}

	private int fishBreed = 1;
	private int sharkStarve = 5;
	private int sharkBreed = 20;

	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors(); //adjacent is always closest 4
		
		if(currentCell.getType().equals("fish")){
			//if(currentCell.getTime()>=fishBreed){ //if enough time has passed to breed...
				for(int i=0; i<neighbors.length; i++){
					if(neighbors[i].getType().equals("empty")){
						//neighbors[i].setType("fish"); place fish
						i=neighbors.length;
						//currentCell.setBreedTime(0); //reset time to breed
					}
				}
			//}
				//move fish to adjacent empty area
		}
		
		if(currentCell.getType().equals("shark")){
			//if(currentCell.getTime()>=sharkBreed){ //if enough time has passed to breed...
			for(int i=0; i<neighbors.length; i++){
				if(neighbors[i].getType().equals("empty")){
					//neighbors[i].setType("shark"); place shark
					i=neighbors.length;
					//currentCell.setBreedTime(0); //reset time to breed
				}
			}
			//}
			for(int j=0; j<neighbors.length; j++){ //check to eat fish
				if(neighbors[j].getType().equals("fish")){
					//neighbors[i].setType("empty"); eat fish
					j=neighbors.length;
					//currentCell.setStarveTime(0); //reset time to breed
				}
			}
			
			/*check starve time
			if(currentCell.getTime()>=sharkStarve){
				currentCell.setType("empty");
			}*/
			
			
		}
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
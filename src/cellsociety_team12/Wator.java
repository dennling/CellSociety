package cellsociety_team12;


public class Wator extends Game{
	private int fishBreed = 1;
	private int sharkStarve = 5;
	private int sharkBreed = 20;

	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		
		if(currentCell.getType().equals("fish")){
			//if(currentCell.getBreedTime()>=fishBreed){ //if enough time has passed to breed...
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
			//if(currentCell.getBreedTime()>=sharkBreed){ //if enough time has passed to breed...
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
			
			//check starve time
			
			
		}
	}
	
	
}
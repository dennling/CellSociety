package cellsociety_team12;

public class Fire extends Game{
	private Cell[][] myGrid = getGrid();
	private double fireChance = 0.5;

	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();//need to have different neighbor type (only 4)
		if(currentCell.getType().equals("fire")){
			for(int i=0; i<neighbors.length; i++){ 
				double roll = Math.random(); 
				if(neighbors[i].getType().equals("tree")){
					if(roll<=fireChance){
						//neighbors[i].setType("fire"); set neighbor to be on fire
					}
				}
			}
			//currentCell.setType("burnt");
		}
	}

}
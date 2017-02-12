package cellsociety_team12;

import cells.Cell;

public class Ants extends Game{

	public Ants(GameData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Grid createGrid(int dimensions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void gameLogic(Cell currentCell) {
		//antForage
	}

	@Override
	protected String setInitialCellType(int type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void antForage(){ //check if has food, return to nest or look for food
		
	}
	
	private void returnNest(){ //go towards max home pheromes, handles food logic (drop food pheromes)
		
	}
	
	private void findFood(){ //go towards max food pheromes, 
		
	}
	
	private void dropFoodPhero(){
		
	}
	
	private void dropHomePhero(){
		
	}
	
	private void selectLoc(){
		
	}
	
}
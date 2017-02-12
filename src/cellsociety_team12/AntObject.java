package cellsociety_team12;

import cells.AntsCell;

public class AntObject{
	
	private boolean hasFood;
	private AntsCell curr;
	
	public AntObject(boolean food){
		hasFood = food;
	}
	

	
	public void returnNest(AntsCell current){ //go towards max home pheromes, handles food logic (drop food pheromes)
		curr = current;
	}
	
	public void findFood(AntsCell current){ //go towards max food pheromes, 
		curr = current;
	}
	
	private void dropFoodPhero(){
		
	}
	
	private void dropHomePhero(){
		
	}
	
	private void selectLoc(){
		
	}
	
	public boolean hasFood(){
		return hasFood;
	}
}
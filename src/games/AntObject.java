package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import cells.AntsCell;
import cells.Cell;
import grids.Grid;

public class AntObject{

	private boolean hasFood;
	private AntsCell curr;
	Random rand = new Random();
	private Grid myGrid;
	private Cell[][] grid;
	
	public AntObject(boolean food){
		hasFood = food;
		myGrid = getGrid();
		grid = myGrid.getGrid();
	}
	public void returnNest(AntsCell current){ //go towards max home pheromes, handles food logic (drop food pheromes)
		curr = current;
		Cell[] neighbors = current.getNeighbors();
		ArrayList<AntObject> currAnts = current.getAnts(true);
		ArrayList<AntObject> futureAnts = current.getAnts(false);

		
		if(current.getType().equals("nest")){ //if at nest with food, drop the food and wait a turn
			this.hasFood = false;
			return;
		}

		int choice=0;
		int pos[][] = new int[neighbors.length][2];
		int maxP[] = new int[neighbors.length];
		for(int i=0; i<neighbors.length; i++){ //determines where to move next
			AntsCell n = (AntsCell)neighbors[i];
			int phero = n.getPheromes()[0];
			maxP[i] = phero;
			pos[i][0] = n.getX();
			pos[i][1] = n.getY();
		}
		int[] temp = maxP.clone();
		Arrays.sort(temp);
		int c = helper(maxP, temp[rand.nextInt(3)]);
		currAnts.remove(0);
		futureAnts.add(this);
		if(current.getPheromes()[1] <= maxP){
			current.setPheromes(3, current.getPheromes()[1]+maxP); // add food pheromes to future state only if less than max of surrounding
		}

	}

	private int helper(int[] temp, int maxP){
		for(int a=0; a<temp.length; a++){
			int b = 0;
			if(maxP == temp[a]){
				return b;
			}
		}
		return 0;
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
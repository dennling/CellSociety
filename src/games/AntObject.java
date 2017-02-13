package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import cells.AntsCell;
import cells.Cell;
import grids.Grid;

/* Object that holds a boolean value and logic for ant movement. Takes care of pheromone and food dropping.
 * Directly tied to "Ants" class through gameLogic method.
 * In all cases type 0 means the the ant has food, and 1 means the ant does not have food.
 */
public class AntObject{
	private static final int MAX_PH = 1000; //Maximum amount of pheromones per cell
	private boolean hasFood;
	Random rand = new Random();
	private Grid myGrid;
	private Cell[][] grid;
	
	public AntObject(boolean food, Grid grids){
		hasFood = food;
		myGrid = grids;
		grid = myGrid.getGrid();
	}
	
	/*
	 * Handles action when ant has food
	 */
	public void returnNest(AntsCell current){ //go towards max home pheromes, handles food logic (drop food pheromes)
		checkCell("nest", current);
		int max = selectLoc(0, current); 
		dropPhero(max,current,0);
	}

	/*
	 * Handles action when ant has no food
	 */
	public void findFood(AntsCell current){ 
		checkCell("food",current);
		int max = selectLoc(1,current);
		dropPhero(max,current,1);
	}

	/*
	 * Checks if ant is at FOOD/NEST
	 */
	private void checkCell(String type, AntsCell current){
		if(current.getType().equals(type)){ //if at nest with food, drop the food and wait a turn
			this.hasFood = false;
			current.setPheromes(2, MAX_PH);
			return;
		}
	}
	
	/*
	 * Drops pheromone type depending on algorithm in paper.
	 */
	private void dropPhero(int max, AntsCell current, int type){ 
		int desired = max - 2;
		int d = desired - current.getPheromes()[1-type];
		if(d>0){
			current.setPheromes(2+type, current.getPheromes()[1-type]+d); 
		}
	}
	
	/* Chooses location to moved based on max pheromone in neighboring cell. 
	 * Randomly picks which place to check first to randomize ties.
	 */
	private int selectLoc(int type, AntsCell current){
		Cell[] neighbors = current.getNeighbors();
		int max = 0;
		int pos[] = new int[2];
		ArrayList<Integer> randomize = new ArrayList<Integer>();
		for(int z=0; z<neighbors.length; z++){
			randomize.add(z);
		}
		Collections.shuffle(randomize);
		for(int z: randomize){
			AntsCell n = (AntsCell)neighbors[z];
			int phero = n.getPheromes()[0+type];
			if(phero >= max){
				max = phero;
				pos[0] = n.getX();
				pos[1] = n.getY();
			}
		}
		AntsCell placer = (AntsCell)grid[pos[0]][pos[1]];
		ArrayList<AntObject> futureAnts = placer.getAnts(false);
		if(type == 0) futureAnts.add(new AntObject(true, myGrid));
		else futureAnts.add(new AntObject(false, myGrid));
		return max;
	}

	public boolean getFood(){
		return hasFood;
	}
}
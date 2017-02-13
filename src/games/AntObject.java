package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import cells.AntsCell;
import cells.Cell;
import cells.WatorCell;
import grids.Grid;

public class AntObject{
	private static int MAX_PH = 1000;
	private boolean hasFood;
	Random rand = new Random();
	private Grid myGrid;
	private Cell[][] grid;
	
	public AntObject(boolean food, Grid grids){
		hasFood = food;
		myGrid = grids;
		grid = myGrid.getGrid();
	}
	public void returnNest(AntsCell current){ //go towards max home pheromes, handles food logic (drop food pheromes)
		checkCell("nest", current);
		int max = selectLoc(0, current); 
		
		dropPhero(max,current,0);
	}

	public void findFood(AntsCell current){ //go towards max food pheromes,
		checkCell("food",current);
		int max = selectLoc(1,current);
		dropPhero(max,current,1);
	}

	private void checkCell(String type, AntsCell current){
		if(current.getType().equals(type)){ //if at nest with food, drop the food and wait a turn
			this.hasFood = false;
			current.setPheromes(2, MAX_PH);
			return;
		}
	}
	
	private void dropPhero(int max, AntsCell current, int type){ //0 = food, 1 = home
		int desired = max - 2;
		int d = desired - current.getPheromes()[1-type];
		if(d>0){
			current.setPheromes(2+type, current.getPheromes()[1-type]+d); // add food pheromes to future state only if less than max of surrounding
		}
	}


	private int selectLoc(int type, AntsCell current){
		Cell[] neighbors = current.getNeighbors();
		//int pos[][] = new int[neighbors.length][2];
		int maxP[] = new int[neighbors.length];
		int max = 0;
		int pos[] = new int[2];
		ArrayList<Integer> randomize = new ArrayList<Integer>();

		for(int z=0; z<neighbors.length; z++){
			randomize.add(z);
		}
		Collections.shuffle(randomize);
		for(int z: randomize){
			
		
		//for(int i=0; i<neighbors.length; i++){ //determines where to move next
			AntsCell n = (AntsCell)neighbors[z];
			int phero = n.getPheromes()[0+type];
			if(phero >= max){
				max = phero;
				pos[0] = n.getX();
				pos[1] = n.getY();
			}
//			maxP[i] = phero;
//			pos[i][0] = n.getX();
//			pos[i][1] = n.getY();
			//System.out.println("X :"+pos[i][0]+ " Y: "+pos[i][1]);

		//}
		}
//		int[] temp = maxP.clone();
//		Arrays.sort(temp);
//		int c = helper(maxP, temp[rand.nextInt(3)]);
		
		//System.out.println("x: " + pos[0] +" y: "+ pos[1]);
		//AntsCell placer = (AntsCell)grid[pos[c][0]][pos[c][1]];
		AntsCell placer = (AntsCell)grid[pos[0]][pos[1]];
		ArrayList<AntObject> futureAnts = placer.getAnts(false);
		if(type == 0) futureAnts.add(new AntObject(true, myGrid));
		else futureAnts.add(new AntObject(false, myGrid));

		
		return max;
		
	}

	public boolean hasFood(){
		return hasFood;
	}
}
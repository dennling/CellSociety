package games;

import java.util.ArrayList;
import cells.AntsCell;
import cells.Cell;
import cellsociety_team12.GameData;
import grids.AntsGrid;
import grids.Grid;

/*
 * Simulation logic for Ants Foraging. 
 */
public class Ants extends Game{
	
	private static int NUM_NESTS = 1;
	private int startingAnts;
	private int decreasePhoAmt;
	
	/*
	 * Constructor that handles the initial input of ants at the nest.
	 */
	public Ants(GameData data) {
		super(data);
		startingAnts = data.numberAnts();
		decreasePhoAmt = data.decAmount();
		String[][] positions = data.getInitialPositions();
		for(String[] i: positions){
			if(i[2].equals("nest")){
				int x = Integer.parseInt(i[0]);
				int y = Integer.parseInt(i[1]);
				ArrayList<AntObject> nestCell = ((AntsCell)(getGrid().getCell(x,y))).getAnts(true);
				for(int a=0; a<startingAnts; a++){
					nestCell.add(new AntObject(false, getGrid()));
				}
			}
		}
	}

	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new AntsGrid(dimensions, this, cellShape);
	}

	/*
	 * Handles all simulation logic. Passes methods through AntObject class
	 */
	@Override
	public void gameLogic(Cell currentCell) {
		AntsCell curr = (AntsCell)currentCell;
		for(AntObject ant: curr.getAnts(true)){
			antForage(ant, curr);
		}
	}
	
	private void antForage(AntObject ant, AntsCell curr){ //check if has food, return to nest or look for food
		if(ant.getFood()) ant.returnNest(curr);
		else ant.findFood(curr);
	}
	
	@Override
	protected void setDefaultPositions(GameData data) {
		for(int i =0; i < NUM_NESTS; i++) {
			randomCellGenerator("nest", data);
			randomCellGenerator("food", data);
		}
	}
	
	public int getDecAmt(){
		return decreasePhoAmt;
	}
}
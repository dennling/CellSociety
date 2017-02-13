package games;

import java.util.ArrayList;
import java.util.Random;

import cells.AntsCell;
import cells.Cell;
import cellsociety_team12.GameData;
import grids.AntsGrid;
import grids.Grid;

public class Ants extends Game{
	
	private int startingAnts;
	private int decreasePhoAmt;
	
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

	@Override
	public void gameLogic(Cell currentCell) {
		AntsCell curr = (AntsCell)currentCell;
		for(AntObject ant: curr.getAnts(true)){
			antForage(ant, curr);
		}
		
	}
	
	private void antForage(AntObject ant, AntsCell curr){ //check if has food, return to nest or look for food
		if(ant.hasFood()) ant.returnNest(curr);
		else ant.findFood(curr);
	}


	@Override
	protected void setDefaultPositions(GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType("ground");
	}
	
	public int getDecAmt(){
		return decreasePhoAmt;
	}
}
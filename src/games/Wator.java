package games;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import cells.Cell;
import cells.WatorCell;
import cellsociety_team12.GameData;
import grids.Grid;
import grids.WatorGrid;

public class Wator extends Game{
	public Wator(GameData data) {
		super(data);

		fishBreed = data.getFishBreed();
		sharkBreed = data.getSharkBreed();
		sharkStarve = data.getSharkStarve();
		randomize = new ArrayList<Integer>();
		myGrid = getGrid();
		grid = myGrid.getGrid();
	}

	private Grid myGrid;
	private Cell[][] grid;
	private double fishBreed;
	private double sharkStarve;
	private double sharkBreed;
	private ArrayList<Integer> randomize;
	private boolean moved;
	private boolean eaten;

	@Override

	public void gameLogic(Cell currentCell) {
		WatorCell currCell = (WatorCell)currentCell;
		Cell[] neighbors = currentCell.getNeighbors(); //adjacent is always closest 4

		for(int z=0; z<neighbors.length; z++){
			randomize.add(z);
		}
		Collections.shuffle(randomize);

		if(currentCell.getType().equals("fish") && !currCell.getFutureType().equals("shark")){
			for(int i: randomize){;			
			if(neighbors[i].getType().equals("neighbor")) continue;

			WatorCell checker = (WatorCell)grid[neighbors[i].getX()][neighbors[i].getY()];
			if(neighbors[i].getType().equals("empty") && checker.getFutureType().equals("empty")){
				if(currCell.getTime()>=fishBreed){
					checker.setFutureType("fish", 0, 0);
					currCell.setFutureType("fish", 0, 0);
					moved = true;
				}	
				else{
					checker.setFutureType("fish",currCell.getTime()+1,0);
					currCell.setFutureType("empty", 0, 0);
					moved=true;
				}
				break;
			}											
			}
			if(!moved && currCell.getFutureType().equals("empty")){
				currCell.setFutureType("fish",currCell.getTime()+1,0);
			}
		}	
		if(currentCell.getType().equals("shark")){			
			loopNeighbors(neighbors, currCell, "fish",false,0);

			if(currCell.getSharkTime()>=sharkStarve){ //shark starves and dies
				currCell.setFutureType("empty",0,0);
			}	
			else if(!eaten && !moved){ //move if haven't eaten
				loopNeighbors(neighbors, currCell, "empty",true,currCell.getSharkTime()+1);
			}

			if(!eaten && !moved && currCell.getFutureType().equals("empty")){
				currCell.setFutureType("fish",currCell.getTime()+1,currCell.getSharkTime()+1);
			}
		}
	}

	private void loopNeighbors(Cell[] neighbors, WatorCell currCell, String type, boolean action, int starve){ //iteration for sharks
		for(int i: randomize){
			WatorCell checker = (WatorCell)grid[neighbors[i].getX()][neighbors[i].getY()];
			boolean fishy = ((WatorCell)neighbors[i]).getType().equals(type);

			if(fishy && checker.getFutureType().equals("empty")){
				if(action) moved = true;
				else eaten = true;
				checker.setFutureType("shark",currCell.getTime()+1,starve);
				currCell.setFutureType("empty",0,0);
				breed(currCell,checker);
				break;
			}
		}
	}

	private void breed(WatorCell current, WatorCell future){
		if(future.getTime() >= sharkBreed){
			current.setFutureType("shark",0,0);
			future.setFutureType("shark",0,future.getSharkTime());
		}
	}

	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new WatorGrid(dimensions, this, cellShape);
	}

	@Override
	protected void setDefaultPositions(GameData data) {
		randomCellGenerator("fish", data);
	}

	/*
	@Override
>>>>>>> master
	protected String setInitialCellType(int type) {
		if(type == 0) return "fish";
		else if(type == 1) return "shark";
		else return "empty";
	}*/
}
package cellsociety_team12;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cells.Cell;
import cells.WatorCell;

public class Wator extends Game{
	public Wator(GameData data) {
		super(data);

		fishBreed = data.getFishBreed();
		sharkBreed = data.getSharkBreed();
		sharkStarve = data.getSharkStarve();
	}

	private Grid myGrid = getGrid();
	private Cell[][] grid = myGrid.getGrid();
	private double fishBreed;
	private double sharkStarve;
	private double sharkBreed;

	@Override
	protected void gameLogic(Cell currentCell) {
		WatorCell currCell = (WatorCell)currentCell;
		Cell[] neighbors = currentCell.getNeighbors(); //adjacent is always closest 4
		boolean moved = false;
		
		ArrayList<Integer> randomize = new ArrayList<Integer>();
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
			boolean eaten = false;

			for(int i: randomize){
				WatorCell checker = (WatorCell)grid[neighbors[i].getX()][neighbors[i].getY()];
				boolean fishy = ((WatorCell)neighbors[i]).getType().equals("fish");

				if(fishy && checker.getFutureType().equals("empty")){ //eat fish
					eaten = true;
					checker.setFutureType("shark",currCell.getTime()+1,0);
					currCell.setFutureType("empty",0,0);
					breed(currCell,checker);
					break;
				}
			}

			if(currCell.getSharkTime()>=sharkStarve){ //shark starves and dies
				currCell.setFutureType("empty",0,0);
			}	

			else if(!eaten && !moved){ //move if haven't eaten
				for(int k: randomize){
					boolean empty = ((WatorCell)neighbors[k]).getType().equals("empty");
					WatorCell checker = (WatorCell)grid[neighbors[k].getX()][neighbors[k].getY()];
					if(empty && checker.getFutureType().equals("empty")){
						moved = true;
						checker.setFutureType("shark",currCell.getTime()+1, currCell.getSharkTime()+1);
						currCell.setFutureType("empty", 0, 0);
						breed(currCell, checker);
						break;
					}
				}
			}
			
			if(!eaten && !moved && currCell.getFutureType().equals("empty")){
				currCell.setFutureType("fish",currCell.getTime()+1,currCell.getSharkTime()+1);
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
	protected Grid createGrid(int dimensions) {
		return new WatorGrid(dimensions, this);
	}

	@Override
	protected void setDefaultPositions(GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType("fish");
	}

	@Override
	protected String setInitialCellType(int type) {
		if(type == 0) return "fish";
		else if(type == 1) return "shark";
		else return "empty";
	}
}
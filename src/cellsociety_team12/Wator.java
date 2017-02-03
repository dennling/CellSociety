 package cellsociety_team12;

import java.util.Random;

import cells.Cell;
import cells.WatorCell;

public class Wator extends Game{
	public Wator(GameData data) {
		super(data);
	}
	
	private Grid myGrid = getGrid();
	private Cell[][] grid = myGrid.getGrid();
	private int fishBreed = 1;
	private int sharkStarve = 5;
	private int sharkBreed = 20;

	@Override
	protected void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors(); //adjacent is always closest 4
		
		if(currentCell.getType().equals("fish")){
			for(int i=0; i<neighbors.length; i++){
				Cell checker = grid[neighbors[i].getX()][neighbors[i].getY()];
				boolean moved = false;
				if(neighbors[i].getType().equals("empty") && checker.getType().equals("empty")){
					if(((WatorCell)currentCell).getTime()>=fishBreed){ //if enough time has passed to breed...
						checker.setType("fish"); //place fish
						((WatorCell)currentCell).setTime(0); //reset time to breed
					}		
					else if(!moved){
						checker.setType("fish");
						moved=true;
					}
				}
			}		
		}
		
		if(currentCell.getType().equals("shark")){
			if(((WatorCell) currentCell).getTime()>=sharkBreed){ //if enough time has passed to breed...
			for(int i=0; i<neighbors.length; i++){
				Cell checker = grid[neighbors[i].getX()][neighbors[i].getY()];
				if(neighbors[i].getType().equals("empty") && checker.getType().equals("empty")){
					checker.setType("shark"); //place shark
					i=neighbors.length;
					((WatorCell) currentCell).setTime(0); //reset time to breed
				}
			}
			}
			for(int j=0; j<neighbors.length; j++){ //check to eat fish
				if(neighbors[j].getType().equals("fish")){
					neighbors[j].setType("empty"); //eat fish
					j=neighbors.length;
					((WatorCell)currentCell).setSharkTime(0); //reset time to starve
				}
			}
			
			if(((WatorCell)currentCell).getSharkTime()>=sharkStarve){
				currentCell.setType("empty");
			}	
		}
		
		((WatorCell)currentCell).setTime(((WatorCell)currentCell).getTime()+1);
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
	protected String setInitialCellType() {
		return "empty";
	}
	
}
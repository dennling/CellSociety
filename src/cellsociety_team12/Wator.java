package cellsociety_team12;

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
		Cell[] neighbors = currentCell.getNeighbors(); //adjacent is always closest 4

		if(currentCell.getType().equals("fish")){
			for(int i=0; i<neighbors.length; i++){
				Cell checker = grid[neighbors[i].getX()][neighbors[i].getY()];
				//System.out.println(((WatorCell)checker).getTime());
				boolean moved = false;
				if(neighbors[i].getType().equals("empty") && checker.getType().equals("empty")){
					if(((WatorCell)currentCell).getTime()>=fishBreed){ //if enough time has passed to breed...
						checker.setType("fish"); //place fish
						((WatorCell)checker).setTime(0); //reset time to breed
						((WatorCell)currentCell).setTime(0); //reset time to breed
					}		
					else if(!moved){
						checker.setType("fish");
						((WatorCell)checker).setTime(0); //reset time to breed
						moved=true;
					}
				}
			}		
		}

		if(currentCell.getType().equals("shark")){
			boolean eaten = false;
			boolean moved = false;

			for(int j=0; j<neighbors.length; j++){ //check to eat fish
				if(neighbors[j].getType().equals("fish") && !eaten){
					Cell checker = grid[neighbors[j].getX()][neighbors[j].getY()];
					eaten = true;
					checker.setType("empty"); //eat fish
					((WatorCell)checker).setTime(0);
					((WatorCell)checker).setSharkTime(0);					
					j=neighbors.length;
					((WatorCell)currentCell).setSharkTime(0); //reset time to starve
				}
			}
			if(((WatorCell)currentCell).getSharkTime()>=sharkStarve){ //shark dies
				currentCell.setType("empty");
				((WatorCell)currentCell).setTime(0);
				((WatorCell)currentCell).setSharkTime(0);
			}	
			else if(!eaten){
				for(int k=0; k<neighbors.length; k++){
					Cell checker = grid[neighbors[k].getX()][neighbors[k].getY()];
					if(neighbors[k].getType().equals("empty") && !moved){
						moved = true;
						checker.setType("shark");
						currentCell.setType("empty");
						((WatorCell)currentCell).setTime(0);
						((WatorCell)currentCell).setSharkTime(0);

					}
				}
			}

			
		}

		if(((WatorCell) currentCell).getTime()>=sharkBreed){ //if enough time has passed to breed...
			for(int i=0; i<neighbors.length; i++){
				Cell checker = grid[neighbors[i].getX()][neighbors[i].getY()];
				if(neighbors[i].getType().equals("empty") && checker.getType().equals("empty")){
					checker.setType("shark"); //place shark
					((WatorCell)checker).setTime(0);
					((WatorCell)checker).setSharkTime(0);
					i=neighbors.length;
					((WatorCell) currentCell).setTime(0); //reset time to breed
				}
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
	protected String setInitialCellType(int type) {
		if(type == 0) return "fish";
		else if(type == 1) return "shark";
		else return "empty";
	}
	

}
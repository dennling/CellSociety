package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import cells.Cell;
import cellsociety_team12.GameData;
import grids.Grid;
import grids.SegregationGrid;

public class Segregation extends Game{


	private Grid myGrid = getGrid();
	private Cell[][] grid = myGrid.getGrid();
	private double propThreshold;
	private double countOne;
	private double countTwo;
	private double prop;
	
	public Segregation(GameData data) {
		super(data);
		propThreshold = data.getProb();
	}
	
	private void initialize(){
		countOne = 0;
		countTwo = 0;
		prop = 0;
	}
	
	@Override
	public void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		initialize();

		countNeighbors(neighbors);
		calcProp(currentCell);
		
		ArrayList<Integer> randomize = new ArrayList<Integer>();
		for(int z=0; z<grid.length; z++){
			randomize.add(z);
		}
		Collections.shuffle(randomize);
		
		ArrayList<Integer> randomize2 = new ArrayList<Integer>();
		for(int z=0; z<grid.length; z++){
			randomize2.add(z);
		}
		Collections.shuffle(randomize2);
		
		if(prop<propThreshold){ //if cell is dissatisfied, move to empty space. pick first empty from top.
			for(int i: randomize){
				for(int j: randomize2){
					if(grid[i][j].getType().equals("empty")) {
						grid[i][j].setType(currentCell.getType());
						currentCell.setType("empty");
						break;
					}
				}
			}
		}
			
	}
	
	private void countNeighbors(Cell[] neighbors){
		for(Cell iter: neighbors){ //iterate through neighbors, counting how many neighbors of each type
			if(iter.getType().equals("one")) countOne++;
			else if(iter.getType().equals("two")) countTwo++;
		}
	}
	
	private void calcProp(Cell currentCell){
		if(currentCell.getType().equals("one")) prop = countOne/(countTwo+countOne); //calc proportion of common neighbors
		else prop = countTwo/(countTwo+countOne);
	}

	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new SegregationGrid(dimensions, this, cellShape);
	}
	
	@Override
	protected String setInitialCellType(int type) {
		if(type == 0) return "one";
		else if(type == 1) return "two";
		else return "empty";
	}

}
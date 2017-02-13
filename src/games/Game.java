package games;

import java.util.Arrays;
import java.util.Random;

import cells.Cell;
import cellsociety_team12.GameData;
import cellsociety_team12.XMLException;
import grids.Grid;


public abstract class Game {
	
	private Grid myGrid;
	private String gameName;
	private GameData myData;
	
	public Game(GameData data) {
		myData = data;
		gameName = myData.getGameType();
		myGrid = createGrid(myData.getDimensions(), myData.getCellShape());
		setInitialPositions();
		myGrid.updateCellNeighbors();
	}
	
	public void setInitialPositions() {	
		String[][] positions = myData.getInitialPositions();
		if (positions.length == 0) {
			setDefaultPositions(myData);
		} else {
			for (String[] each : positions) {
				//could also simply continue and skip this initial state
				if (each.length != 3) {
					throw new XMLException("Initial State not Given for Cell %s", Arrays.toString(each));
				}
				int x = Integer.parseInt(each[0]);
				int y = Integer.parseInt(each[1]);
				if (x < getGrid().getRow(0).length && x >= 0 && y < getGrid().getRow(0).length && y >= 0){ 
					getGrid().getCell(x,y).setType(each[2]);
				}
				
			}
		}
	}
	
	protected abstract Grid createGrid(int dimensions, String cellShape);
	public abstract void gameLogic(Cell currentCell);
	protected abstract void setDefaultPositions(GameData data);
	
	protected void randomCellGenerator(String type, GameData data) {
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType(type);
	}
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public void updateGrid() {
		myGrid.updateGrid();
	}

	public String getName(){
		return gameName;
	}
	
	public GameData getData() {
		return myData;
	}
	
}

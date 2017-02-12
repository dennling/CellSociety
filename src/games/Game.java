package games;

import cells.Cell;
import cellsociety_team12.GameData;
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
	
	private void setInitialPositions() {	
		String[] pos1 = myData.getPositions1(); 

		if (myData.getInitialPositions(myData.getPositions1()).length == 0) {
				setDefaultPositions(myData);
		} 
		else {
			String[] pos2 = myData.getPositions2(); 
			String[] pos3 = myData.getPositions3(); 
			if(pos1.length > 0) setPositions(myData, pos1, 0);
			if(pos2.length > 0) setPositions(myData, pos2, 1);
			if(pos3.length > 0) setPositions(myData, pos3, 2);

		}
	}
	
	private void setPositions(GameData data, String[] initialPositions, int type){
		int[][] positions = data.getInitialPositions(initialPositions);
		for (int[] each : positions) {
			getGrid().getCell(each[0],each[1]).setType(setInitialCellType(type));
		}
	}
	
	protected abstract Grid createGrid(int dimensions, String cellShape);
	public abstract void gameLogic(Cell currentCell);

	protected abstract String setInitialCellType(int type);
	protected abstract void setDefaultPositions(GameData data);
	
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

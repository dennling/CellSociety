package games;

import cells.Cell;
import cellsociety_team12.GameData;
import grids.Grid;


public abstract class Game {
	
	private Grid myGrid;
	private String gameName;
	
	public Game(GameData data) {
		gameName = data.getGameType();
		myGrid = createGrid(data.getDimensions());
		setInitialPositions(data);
		myGrid.updateCellNeighbors();
	}
	
	private void setInitialPositions(GameData data) {	
		String[] pos1 = data.getPositions1(); 

		if (data.getInitialPositions(data.getPositions1()).length == 0) {
				setDefaultPositions(data);
		} 
		else {
			String[] pos2 = data.getPositions2(); 
			String[] pos3 = data.getPositions3(); 
			if(pos1.length > 0) setPositions(data, pos1, 0);
			if(pos2.length > 0) setPositions(data, pos2, 1);
			if(pos3.length > 0) setPositions(data, pos3, 2);

		}
	}
	
	private void setPositions(GameData data, String[] initialPositions, int type){
		int[][] positions = data.getInitialPositions(initialPositions);
		for (int[] each : positions) {
			getGrid().getCell(each[0],each[1]).setType(setInitialCellType(type));
		}
	}
	
	
	protected abstract Grid createGrid(int dimensions);
	
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
	
}

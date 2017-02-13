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
		String[][] positions = myData.getInitialPositions();
		if (positions.length == 0) {
			setDefaultPositions(myData);
		} else {
			for (String[] each : positions) {
				int x = Integer.parseInt(each[0]);
				int y = Integer.parseInt(each[1]);
				getGrid().getCell(x,y).setType(each[2]);
			}
		}
	}
	
	protected abstract Grid createGrid(int dimensions, String cellShape);
	
	public abstract void gameLogic(Cell currentCell);

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

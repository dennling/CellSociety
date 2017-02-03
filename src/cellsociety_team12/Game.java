package cellsociety_team12;

import javafx.scene.shape.Rectangle;

import javax.xml.parsers.SAXParser;

import cells.Cell;

public abstract class Game {
	
	private Grid myGrid;
	
	
	public Game(GameData data) {
		myGrid = createGrid(data.getDimensions());
		setInitialPositions(data);
		
	}
	
	private void setInitialPositions(GameData data) {
		if (data.getInitialPositions().length == 0) {
			setDefaultPositions(data);
		} else {
			int[][] positions = data.getInitialPositions();
			for (int[] each : positions) {
				getGrid().getCell(each[0],each[1]).setType(setInitialCellType());
			}
		}
	}
	
	
	protected abstract Grid createGrid(int dimensions);
	protected abstract void gameLogic(Cell currentCell);
	protected abstract String setInitialCellType();
	protected abstract void setDefaultPositions(GameData data);
	
	public Grid getGrid() {
		return myGrid;
	}
	
	public void updateGrid() {
		myGrid.updateGrid();
	}
	
	
}

package cellsociety_team12;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import cells.Cell;

import javax.xml.parsers.SAXParser;

public abstract class Game {
	
	//private Cell[][] myGrid;
	private Grid myGrid;
	
	
	public Game(GameData data) {
		myGrid = createGrid(data.getDimensions());
		setInitialPositions(data);
		
	}
	
	protected abstract Grid createGrid(int dimensions);
	
	protected abstract void gameLogic(Cell currentCell);
	
	protected abstract void setInitialPositions(GameData data);
	
	protected abstract void setDefaultPositions(GameData data);
	
	public Grid getGrid() {
		return myGrid;
	}
	
	
}

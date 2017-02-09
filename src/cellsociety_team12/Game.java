package cellsociety_team12;

import java.util.Random;

import cells.Cell;


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
	
	protected abstract void gameLogic(Cell currentCell);

	protected abstract String setInitialCellType(int type);

	private void setDefaultPositions(GameData data){
		Random numberGenerator = new Random();
		int randomX = numberGenerator.nextInt(data.getDimensions());
		int randomY = numberGenerator.nextInt(data.getDimensions());
		getGrid().getCell(randomX, randomY).setType(getType());
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
	
	private String getType(){
		if(gameName.equals("Fire")) return "fire";
		else if(gameName.equals("Segregation")) return "one";
		else if(gameName.equals("Wator")) return "fish";
		else return "alive";
	}	
}

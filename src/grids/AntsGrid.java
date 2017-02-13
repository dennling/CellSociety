package grids;
import java.util.ArrayList;

import cells.AntsCell;
import cells.Cell;
import games.AntObject;
import games.Ants;
import games.Game;

/*
 * Grid class for ant simulation.
 */
public class AntsGrid extends Grid{

	private Cell[][] myGrid; 
	private Ants myGame;

	public AntsGrid(int dimensions, Game game, String cellShape) {
		super(dimensions, game, cellShape);
		myGrid = getGrid();
		myGame = (Ants)getGame();
	}

	@Override
	protected AntsCell cellType(int x, int y, String cellShape) {
		return new AntsCell(x, y, "ground", cellShape, getGame().getData().getGridType(), getGame().getData().getNeighborType(), new ArrayList<AntObject>(), new ArrayList<AntObject>(), new int[4]);
	}

	/*
	 * First run through updates grid based on current states. Second run through changes future into current states.
	 */
	@Override
	public void updateGrid(){
		updateMe(false);
		updateMe(true);
		updateCellNeighbors();
	}
	
	/*
	 * Helper method to update grid and to update future into current states.
	 */
	private void updateMe(boolean first){
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				AntsCell curr = (AntsCell)currentCell;
				if(!first) myGame.gameLogic(currentCell);
				else{
					ArrayList<AntObject> currTemp = curr.getAnts(true);
					ArrayList<AntObject> currFuture = curr.getAnts(false);
					coloring(curr, currFuture);
					currTemp = new ArrayList<>(curr.getAnts(false));
					curr.setAnts(currTemp);
					currFuture.clear();
					curr.setPheromes(0, curr.getPheromes()[2] - myGame.getDecAmt());
					curr.setPheromes(1, curr.getPheromes()[3] - myGame.getDecAmt());
					curr.setPheromes(2, 0);
					curr.setPheromes(3, 0);		
				}
			}
		}
	}
	
	/*
	 * Update coloring of cells based on ant population
	 */
	private void coloring(AntsCell curr, ArrayList<AntObject> currFuture){
		double color = currFuture.size();
		if(curr.getType().equals("ground")){
			if((255-color*25)>=0 && (255-color*25)<=255) {
				curr.updateColor(255 - color*25);
			}
			else if((255 - color*25)<=0){
				curr.updateColor(0);
			}
			else{
				curr.updateColor(255);
			}
		}	
	}

	@Override
	protected String resetType() {
		return "ground";
	}

}
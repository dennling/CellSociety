package grids;
import java.util.ArrayList;

import cells.AntsCell;
import cells.Cell;
import cells.WatorCell;
import games.AntObject;
import games.Ants;
import games.Game;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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

	@Override
	public void updateGrid(){
		updateMe(false);
		updateMe(true);
		updateCellNeighbors();
	}
	private void updateMe(boolean done){
		for (int i = 0; i < myGrid.length; i++) {
			for (int k = 0; k < myGrid.length; k++) {
				Cell currentCell = myGrid[i][k];
				if(!done) myGame.gameLogic(currentCell);
				else{
					AntsCell curr = (AntsCell)currentCell;
					ArrayList<AntObject> currTemp = curr.getAnts(true);
					currTemp = new ArrayList<>(curr.getAnts(false));
					curr.setAnts(currTemp);
					ArrayList<AntObject> currFuture = curr.getAnts(false);
					
					//System.out.println("curr size: "+ currTemp.size()+ " future size: "+ currFuture.size());
					double color = currFuture.size();
					currFuture.clear();
					System.out.println(currFuture.size() + " future size");
					
					curr.setPheromes(0, curr.getPheromes()[2] - myGame.getDecAmt());
					curr.setPheromes(1, curr.getPheromes()[3] - myGame.getDecAmt());
					curr.setPheromes(2, 0);
					curr.setPheromes(3, 0);
					if(curr.getType().equals("ground") && color*20<= 255) {
						curr.updateColor(color*20);
					}
				}
			}
		}
	}

}
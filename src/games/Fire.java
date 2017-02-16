//This entire file is part of my masterpiece.
//Dennis Ling

/* 
 * This class is a subclass of Game (the other part of my masterpiece).
 * This is well designed because it represents what I've learned about using super and subclasses to have extensible, flexible code.
 * This code is one example of how to extend our program to allow for more simulations.
 * The gameLogic method (along with all of the other methods) is also concise and understandable, with appropriate variable and method names.
 * Both of my master piece codes also continue from what I've learned from the last project to not have public non-static variables, and to not have static methods.
 */

package games;

import cells.Cell;
import cellsociety_team12.GameData;
import grids.FireGrid;
import grids.Grid;

/*
 * Class for the Fire simulation, subclass of game
 */
public class Fire extends Game{

	private double fireChance;

	public Fire(GameData data) {
		super(data);
		fireChance = data.getProb();
	}

	/*
	 * Handles the logic of how cells are updated in the Fire simulation
	 */
	@Override
	public void gameLogic(Cell currentCell) {
		Cell[] neighbors = currentCell.getNeighbors();
		String type = currentCell.getType();
		if(type.equals("tree")){
			for(int i=0; i<neighbors.length; i++){ 
				if(neighbors[i].getType().equals("fire")){
					if(Math.random()<=fireChance){
						currentCell.setType("fire"); 
					}
				}
			}
		}
		else if(type.equals("fire")){
			currentCell.setType("empty");
		}
	}
	
	@Override
	protected Grid createGrid(int dimensions, String cellShape) {
		return new FireGrid(dimensions, this, cellShape);
	}

	@Override
	protected void setDefaultPositions(GameData data) {
		randomCellGenerator("fire", data);
	}
}
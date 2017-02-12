package cellsociety_team12;

import java.util.ArrayList;
import cells.AntsCell;
import cells.Cell;

public class Ants extends Game{

	private ArrayList<AntObject> currAnts;
	private ArrayList<AntObject> futureAnts;
	private int[] phero;
	
	public Ants(GameData data) {
		super(data);
		
		//get data for ant starting positions, nest, and food source
	}

	@Override
	protected Grid createGrid(int dimensions) {
		return new AntsGrid(dimensions, this);
	}

	@Override
	protected void gameLogic(Cell currentCell) {
		AntsCell curr = (AntsCell)currentCell;
		for(AntObject ant: currAnts){
			antForage(ant, curr);
		}
	}

	@Override
	protected String setInitialCellType(int type) {
		if(type==0) return "nest";
		else if(type ==1) return "food";
		else return "ground";
	}
	
	private void antForage(AntObject ant, AntsCell curr){ //check if has food, return to nest or look for food
		if(ant.hasFood()) ant.returnNest(curr);
		else ant.findFood(curr);
	}	
}
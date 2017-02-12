package cells;

import java.util.ArrayList;

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AntsCell extends Cell{

	private ArrayList<Boolean> current; //bools keep track of if ant has food
	private ArrayList<Boolean> future;
	private int[] phero; //0 = home pheromes, 1 = food pheromes for CURRENT, 2 = home, 3 = food for FUTURE
	
	public AntsCell(int x, int y, String type, String shape, String gridType, String cellType, ArrayList<Boolean> currWorkers, ArrayList<Boolean> futureWorkers, int[] pheromes) {
		super(x, y, type, shape, gridType, cellType);
		current = currWorkers;
		future = futureWorkers;
		phero = pheromes;
	}

	@Override
	public Cell specifyNeighborCell() {
		return new AntsCell(0, 0, "neighbor", getShapeString(), "","",current, future, phero);
	}

	@Override
	public int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
			{1, 0}, {1, -1}, {0, -1}};	
			return possibleNeighbors;
	}

	@Override
	protected void setColor() {
		//count number in arraylist, put dots based on #
		//shade based on pheromes
		getShape().setStroke(Color.BLACK);		
	}
	
	public ArrayList<Boolean> getAnts(boolean type){
		if(type) return current;
		else return future;
	}
	
	public int[] getPheromes(){
		return phero;
	}
	
	public void setPheromes(int pos, int num){
		phero[pos] = num;
	}

	@Override
	public void checkType(String type) {
		if (!(type.equals("nest") || type.equals("food") || type.equals("ground"))) {
			throw new XMLException("This is not a valid cell type for the chosen game %s", type);
		}
		return;
	}
	
	
}
package cells;

import java.util.ArrayList;

import cellsociety_team12.XMLException;
import games.AntObject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class AntsCell extends Cell{

	private ArrayList<AntObject> current; //bools keep track of if ant has food
	private ArrayList<AntObject> future;
	private int[] phero; //0 = home pheromes, 1 = food pheromes for CURRENT, 2 = home, 3 = food for FUTURE
	
	public AntsCell(int x, int y, String type, String shape, String gridType, String cellType, ArrayList<AntObject> currWorkers, ArrayList<AntObject> futureWorkers, int[] pheromes) {
		super(x, y, type, shape, gridType, cellType);
		current = currWorkers;
		future = futureWorkers;
		phero = new int[4];
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
		if (getType().equals("nest")) {
			getShape().setFill(Color.GREEN);
		} else if (getType().equals("food")) {
			getShape().setFill(Color.RED);
		}
		else{
			getShape().setFill(Color.WHITE);
		}
		getShape().setStroke(Color.BLACK);		
	}
	
	public ArrayList<AntObject> getAnts(boolean type){
		if(type) return current;
		else return future;
	}
	
	public void setAnts(ArrayList<AntObject> newAnts){
		current = newAnts;
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
	
	public void updateColor(double RGB){
		int color = (int) Math.floor(RGB);
		getShape().setFill(Color.rgb(255,color,255));
	}
	
}
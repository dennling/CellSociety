package cells;

import cellsociety_team12.XMLException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WatorCell extends Cell{
	private int breedTime;
	private int timeSharkStarve;
	private String state;

	public WatorCell(int x, int y, String type, String shape, String gridType, String cellType, int timer, int timerShark2, String future){
		super(x,y,type,shape, gridType, cellType);
		breedTime = timer;
		timeSharkStarve = timerShark2;
		state = future;
	}

	@Override
	public Cell specifyNeighborCell() {
		return new WatorCell(0, 0, "neighbor", getShapeString(), "", "", breedTime, timeSharkStarve, "neighbor");
	}

	@Override
	protected void setColor() {
		if (getType().equals("fish")) {
			getShape().setFill(Color.GREEN);
		} else if (getType().equals("shark")) {
			getShape().setFill(Color.ORANGE);
		}
		else{
			getShape().setFill(Color.BLUE);
		}
		getShape().setStroke(Color.BLACK);
	}
	
	public int getTime() {
		return breedTime;
	}
	public int getSharkTime() {
		return timeSharkStarve;
	}
	
	public void setTime(int in) {
		breedTime = in;
	}
	
	public void setSharkTime(int in) {
		timeSharkStarve = in;
	}
	
	public String getFutureType() {
		return state;
	}
	
	public void setFutureType(String futureState) {
		state = futureState;
	}
	
	public void setFutureType(String futureState, int one, int two) {
		state = futureState;
		breedTime = one;
		timeSharkStarve = two;
	}
	
	
	@Override
	public int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, 0}, {0, 1}, 
			{1, 0}, {0, -1}};	
	return possibleNeighbors;
	}
	
	@Override
	public void checkType(String type) {
		if (!(type.equals("fish") || type.equals("shark") || type.equals("empty"))) {
			throw new XMLException("This is not a valid cell type for the chosen game %s", type);
		}
		return;
	}

	@Override
	public void switchType() {
		if (this.getType().equals("fish")){
			this.setType("shark");
		}
		else if (this.getType().equals("empty")){
			this.setType("fish");
		}
		else {
			this.setType("empty");
		}
	}
}

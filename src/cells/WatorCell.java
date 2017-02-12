package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WatorCell extends Cell{
	private int breedTime;
	private int timeSharkStarve;
	private String state;

	public WatorCell(int x, int y, String type, Shape shape, int timer, int timerShark2, String future){
		super(x,y,type,shape);
		breedTime = timer;
		timeSharkStarve = timerShark2;
		state = future;
	}

	@Override
	protected Cell specifyNeighborCell() {
		return new WatorCell(0, 0, "neighbor", new Rectangle(), breedTime, timeSharkStarve, "neighbor");
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
	protected int[][] setPossibleNeighbors()  {
		int[][] possibleNeighbors = new int[][]{{-1, 0}, {0, 1}, 
			{1, 0}, {0, -1}};	
	return possibleNeighbors;
	}
}

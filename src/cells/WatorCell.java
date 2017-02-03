package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WatorCell extends Cell{
	private int breedTime;
	private int timeSharkStarve;

	public WatorCell(int x, int y, String type, Shape shape, int timer, int timerShark2){
		super(x,y,type,shape);
		breedTime = timer;
		timeSharkStarve = timerShark2;
	}

	@Override
	protected Cell specifyNeighborCell() {
		return new WatorCell(0, 0, "neighbor", new Rectangle(), breedTime, timeSharkStarve);
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
	
}

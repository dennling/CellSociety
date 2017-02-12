package cellsociety_team12;

import games.Game;
import graphs.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RectangleDisplay extends SceneBuilder{
	
	public RectangleDisplay(GameData myData, Game game, Graph graph, String styleSheet){
		super(myData, game, graph, styleSheet);
	}

	@Override
	protected void setGrid(Pane cells) {
		for (int i=0; i<myGrid.getNumberOfRows(); i++){
			for (int j=0; j<myGrid.getNumberOfColumns(); j++){
				Rectangle shape = (Rectangle) myGrid.getCell(i, j).getShape();
				shape.setWidth(simulationWidth/(myGrid.getNumberOfRows()));
				shape.setHeight(simulationHeight/(myGrid.getNumberOfColumns()));
				shape.setX(i*shape.getWidth());
				shape.setY(j*shape.getHeight());
				cells.getChildren().add(shape);
			}
		}
	}

}

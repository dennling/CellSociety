package scenes;

import cells.Cell;
import cellsociety_team12.GameData;
import games.Game;
import graphs.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/** 
 * The RectangleDisplay class extends the SceneBuilder superclass, building the entire
 * GUI only filling the grid with properly spaced rectangles. For more detailed documentation 
 * see the SceneBuilder superclass.
 *
 * @author advaitreddy
 *
 */

public class RectangleDisplay extends SceneBuilder{
	
	public RectangleDisplay(GameData myData, Game game, Graph graph, String styleSheet){
		super(myData, game, graph, styleSheet);
	}

	@Override
	/**
	 * Evenly places rectangles throughout the grid.
	 */
	protected void setGrid(Pane cells) {
		for (int i=0; i<myGrid.getNumberOfRows(); i++){
			for (int j=0; j<myGrid.getNumberOfColumns(); j++){
				Cell currentCell = myGrid.getCell(i, j);
				Rectangle shape = (Rectangle) currentCell.getShape();
				shape.setWidth(simulationWidth/(myGrid.getNumberOfRows()));
				shape.setHeight(simulationHeight/(myGrid.getNumberOfColumns()));
				shape.setX(i*shape.getWidth());
				shape.setY(j*shape.getHeight());
				shape.setOnMouseClicked(event -> currentCell.switchType());
				cells.getChildren().add(shape);
			}
		}
	}

}

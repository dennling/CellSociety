package scenes;

import cells.Cell;
import cellsociety_team12.GameData;
import games.Game;
import graphs.Graph;
import grids.Grid;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/** 
 * The HexagonDisplay class extends the SceneBuilder superclass, building the entire
 * GUI only filling the grid with properly spaced hexagons. For more detailed 
 * documentation see the SceneBuilder superclass.
 *
 * @author advaitreddy
 *
 */

public class HexagonDisplay extends SceneBuilder{
	
	public HexagonDisplay(GameData myData, Game game, Graph graph, String styleSheet){
		super(myData, game, graph, styleSheet);
	}

	@Override
	/**
	 * Evenly spaces hexagons throughout grid. The first hexagon in even rows
	 * starts at zero. Otheriwse, the first hexagon in odd rows starts at the
	 * spacer value. 
	 */
	protected void setGrid(Pane cells) {
		double sideLength = calculateHexagonSideLength(myGrid);
		double spacer = sideLength/Math.sqrt(2);
		double xStart = spacer;
		double yStart = 0;
		boolean startedAtZero = true;
		for (int i=0; i<myGrid.getNumberOfRows(); i++){
			for (int j=0; j<myGrid.getNumberOfColumns(); j++){
				Cell currentCell = myGrid.getCell(i, j);
				Polygon hexagon;
				Double[] coordinates = {xStart, yStart,xStart - spacer, yStart + spacer, xStart, yStart + 2*spacer, xStart + sideLength, yStart + 2*spacer, xStart + sideLength + spacer, yStart + spacer, xStart+sideLength, yStart};
				hexagon = (Polygon) currentCell.getShape();
				hexagon.getPoints().addAll(coordinates);
				hexagon.setOnMouseClicked(event -> myGrid.setNeighborOnSwitch(currentCell));
				cells.getChildren().add(hexagon);
				yStart += 2*spacer;
			}
			if (startedAtZero){
				yStart = spacer;
				startedAtZero = false;
			}
			else{
				yStart = 0;
				startedAtZero = true;
			}
			xStart += (sideLength+spacer);
		}
	}
	
	private double calculateHexagonSideLength(Grid grid){
		int sideLengthNum = grid.getNumberOfRows();
		int spacerNum = sideLengthNum + 1;
		double sideWidth =  simulationWidth/((spacerNum/Math.sqrt(2))+sideLengthNum);
		return sideWidth;
	}
}

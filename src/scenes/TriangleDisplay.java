package scenes;

import cells.Cell;
import cellsociety_team12.GameData;
import games.Game;
import graphs.Graph;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

/** 
 * The TriangleDisplay class extends the SceneBuilder superclass, building the entire
 * GUI only filling the grid with properly spaced triangles. For more detailed 
 * documentation see the SceneBuilder superclass.
 *
 * @author advaitreddy
 *
 */

public class TriangleDisplay extends SceneBuilder{

	public TriangleDisplay(GameData myData, Game game, Graph graph, String styleSheet){
		super(myData, game, graph, styleSheet);
	}
	
	@Override
	/**
	 * Evenly spaces triangles throughout the grid. If both row and column
	 * are even or odd, the triangles are upward. If the designated grid spot
	 * has an off row and even column (or vice-versa), the triangle is oriented
	 * downward. If the row is even, the first triangles top point is at 0. 
	 * Otherwise, the first triangle with start at half the calculated base
	 * length.
	 * 
	 */
	protected void setGrid(Pane cells) {
		double halfBaseLength = simulationWidth/(myGrid.getNumberOfRows()+1);
		double height = simulationHeight/myGrid.getNumberOfColumns();
		double xStart = halfBaseLength;
		double yStart = 0;
		for (int i=0; i<myGrid.getNumberOfColumns(); i++){
			for (int j=0; j<myGrid.getNumberOfRows(); j++){
				boolean onEvenRowAndColumn = (i+2) % 2 == 0 && (j+2) % 2 == 0;
				boolean onOddRowAndColumn = (i+2) % 2 != 0 && (j+2) % 2 != 0;
				Cell currentCell = myGrid.getCell(i, j);
				Polygon triangle;
				triangle = (Polygon) myGrid.getCell(i,j).getShape();
				if (onEvenRowAndColumn || onOddRowAndColumn){
					triangle.getPoints().addAll(makeUpwardTriangle(xStart, yStart, halfBaseLength, height));
				}
				else{
					triangle.getPoints().addAll(makeDownwardTriangle(xStart, yStart, halfBaseLength, height));
					xStart += 2*halfBaseLength;
				}
				triangle.setOnMouseClicked(event -> myGrid.setNeighborOnSwitch(currentCell));
				cells.getChildren().add(triangle);
			}
			if ((i+2) % 2 == 0){
				xStart = 0;
			}
			else{
				xStart = halfBaseLength;
			}
			yStart += height;
		}
	}

	private Double[] makeUpwardTriangle(double xStart, double yStart, double halfBaseLength, double height){
		return new Double[]{xStart, yStart, xStart + halfBaseLength, yStart + height, xStart - halfBaseLength, yStart + height};
	}
	
	private Double[] makeDownwardTriangle(double xStart, double yStart, double halfBaseLength, double height){
		return new Double[]{xStart, yStart, xStart + 2*halfBaseLength, yStart, xStart + halfBaseLength, yStart + height};
	}

}

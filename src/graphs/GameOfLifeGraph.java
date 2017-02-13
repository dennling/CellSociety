package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

/** 
 * Creates a Graph object specifically for the Game of Life simulation. See
 * the Graph superclass for more detailed documentation.
 *
 * @author advaitreddy
 *
 */

public class GameOfLifeGraph extends Graph{

	XYChart.Series aliveData;
	XYChart.Series deadData;
	
	public GameOfLifeGraph(){
		super();
	}
	
	@Override
	protected void initializeSeries() {
		aliveData = new XYChart.Series<>();
		aliveData.setName(myResources.getString("AliveLegendText"));
		deadData = new XYChart.Series<>();
		deadData.setName(myResources.getString("DeadLegendText"));
		populationGraph.getData().addAll(aliveData, deadData);
	}

	@Override
	public void updateGraph(double time, Map<String,Integer> cellPopulationMap){
		for (String cellType : cellPopulationMap.keySet()){
			if (cellType.equals("alive")){
				aliveData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("dead")){
				deadData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
		}
	}
	
	public void clear(){
		aliveData.getData().clear();
		deadData.getData().clear();
	}

}

package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

/** 
 * Creates a Graph object specifically for the Segregation simulation. 
 * See the Graph superclass for more detailed documentation.
 *
 * @author advaitreddy
 *
 */

public class SegregationGraph extends Graph{
	
	private XYChart.Series redData;
	private XYChart.Series blueData;
	private XYChart.Series emptyData;
	
	public SegregationGraph(){
		super();
	}

	@Override
	protected void initializeSeries() {
		redData = new XYChart.Series<>();
		redData.setName(myResources.getString("RedLegendText"));
		blueData = new XYChart.Series<>();
		blueData.setName(myResources.getString("BlueLegendText"));
		emptyData = new XYChart.Series<>();
		emptyData.setName(myResources.getString("EmptyLegendText"));
		populationGraph.getData().addAll(redData, blueData, emptyData);
	}

	@Override
	public void updateGraph(double time, Map<String, Integer> cellPopulationMap) {
		for (String cellType : cellPopulationMap.keySet()){
			if (cellType.equals("one")){
				redData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("two")){
				blueData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("empty")){
				emptyData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
		}
	}
	
	public void clear(){
		redData.getData().clear();
		blueData.getData().clear();
		emptyData.getData().clear();
	}

}

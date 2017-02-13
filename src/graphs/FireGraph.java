package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

/** 
 * Creates a Graph object specifically for the Fire simulation. See
 * the Graph superclass for more detailed documentation.
 *
 * @author advaitreddy
 *
 */

public class FireGraph extends Graph{

	private XYChart.Series treeData;
	private XYChart.Series fireData;
	private XYChart.Series emptyData;
	
	public FireGraph(){
		super();
	}

	@Override
	protected void initializeSeries() {
		treeData = new XYChart.Series<>();
		treeData.setName(myResources.getString("TreeLegendText"));
		fireData = new XYChart.Series<>();
		fireData.setName(myResources.getString("FireLegendText"));
		emptyData = new XYChart.Series<>();
		emptyData.setName(myResources.getString("EmptyLegendText"));
		populationGraph.getData().addAll(treeData, fireData, emptyData);
	}

	@Override
	public void updateGraph(double time, Map<String, Integer> cellPopulationMap) {
		for (String cellType : cellPopulationMap.keySet()){
			if (cellType.equals("tree")){
				treeData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("fire")){
				fireData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("empty")){
				emptyData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
		}
	}
	
	public void clear(){
		treeData.getData().clear();
		fireData.getData().clear();
		emptyData.getData().clear();
	}

}

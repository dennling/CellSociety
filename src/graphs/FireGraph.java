package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

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
		treeData.setName("Tree");
		fireData = new XYChart.Series<>();
		fireData.setName("Fire");
		emptyData = new XYChart.Series<>();
		emptyData.setName("Empty");
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

}

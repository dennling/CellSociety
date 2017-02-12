package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

public class WatorGraph extends Graph{
	
	private XYChart.Series fishData;
	private XYChart.Series sharkData;
	private XYChart.Series emptyData;
	
	public WatorGraph(){
		super();
	}

	@Override
	protected void initializeSeries() {
		sharkData = new XYChart.Series<>();
		sharkData.setName(myResources.getString("SharkLegendText"));
		fishData = new XYChart.Series<>();
		fishData.setName(myResources.getString("FishLegendText"));
		emptyData = new XYChart.Series<>();
		emptyData.setName(myResources.getString("EmptyLegendText"));
		populationGraph.getData().add(emptyData);
		populationGraph.getData().add(sharkData);
		populationGraph.getData().add(fishData);
	}

	@Override
	public void updateGraph(double time, Map<String, Integer> cellPopulationMap) {
		for (String cellType : cellPopulationMap.keySet()){
			if (cellType.equals("fish")){
				fishData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("shark")){
				sharkData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("empty")){
				emptyData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
		}
	}
}

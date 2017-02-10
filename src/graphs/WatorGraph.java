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
		sharkData.setName("Shark");
		fishData = new XYChart.Series<>();
		fishData.setName("Fish");
		emptyData = new XYChart.Series<>();
		emptyData.setName("Empty");
		populationGraph.getData().addAll(fishData, sharkData, emptyData);
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

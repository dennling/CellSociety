package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

public class AntGraph extends Graph{
	
	private XYChart.Series nestData;
	private XYChart.Series foodData;
	private XYChart.Series groundData;
	
	public AntGraph(){
		super();
	}

	@Override
	protected void initializeSeries() {
		nestData = new XYChart.Series<>();
		nestData.setName(myResources.getString("NestLegendText"));
		foodData = new XYChart.Series<>();
		foodData.setName(myResources.getString("FoodLegendText"));
		groundData = new XYChart.Series<>();
		groundData.setName(myResources.getString("GroundLegendText"));
		populationGraph.getData().addAll(nestData, foodData, groundData);
	}

	@Override
	public void updateGraph(double time, Map<String, Integer> cellPopulationMap) {
		for (String cellType : cellPopulationMap.keySet()){
			if (cellType.equals("nest")){
				nestData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("food")){
				foodData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
			if (cellType.equals("ground")){
				groundData.getData().add(new XYChart.Data(time, cellPopulationMap.get(cellType)));
			}
		}
	}
	
	public void clear(){
		nestData.getData().clear();
		foodData.getData().clear();
		groundData.getData().clear();
	}

}

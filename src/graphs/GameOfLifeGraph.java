package graphs;

import java.util.Map;

import javafx.scene.chart.XYChart;

public class GameOfLifeGraph extends Graph{

	XYChart.Series aliveData;
	XYChart.Series deadData;
	
	public GameOfLifeGraph(){
		super();
	}
	
	@Override
	protected void initializeSeries() {
		aliveData = new XYChart.Series<>();
		aliveData.setName("Alive");
		deadData = new XYChart.Series<>();
		deadData.setName("Dead");
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

}

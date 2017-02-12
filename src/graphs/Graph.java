package graphs;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public abstract class Graph {

	    private static final String DEFAULT_RESOURCES = "resources/English";
	    
		public LineChart<Number, Number> populationGraph;
		protected ResourceBundle myResources;
		
		public Graph(){
			myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
			NumberAxis timeAxis = new NumberAxis();
			timeAxis.setTickLabelsVisible(false);
			timeAxis.setMinorTickVisible(false);
			timeAxis.setTickMarkVisible(false);
			NumberAxis populationAxis = new NumberAxis();
			populationAxis.setLabel("Cell Population");
			populationAxis.setId("axis");
			populationGraph = new LineChart<Number, Number>(timeAxis, populationAxis);
			populationGraph.setCreateSymbols(false);
			initializeSeries();
		}
		
		protected abstract void initializeSeries();
		
		public abstract void updateGraph(double time, Map<String, Integer> cellPopulationMap);
		
		public XYChart<Number, Number> getGraph(){
			return populationGraph;
		}
}

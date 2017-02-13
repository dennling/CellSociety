package graphs;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/** 
 * The Graph class contains all properties and methods needed to create,
 * update, and manipulate the on-screen graph.
 * 
 * This class is abstract so it should never be called on its own. Instead
 * it should be used whenever the user wants to create a graph but is unsure
 * of what type of graph it should be.
 * 
 * Methods within this class allow for the on-screen graph to be initialized, 
 * updated, and clears.
 *
 * @author advaitreddy
 *
 */

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
		
		public abstract void clear();
}

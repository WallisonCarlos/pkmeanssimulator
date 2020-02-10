package br.com.pkmeanssimulator;

import javax.xml.bind.JAXBException;

import org.jgrapht.alg.interfaces.PartitioningAlgorithm;
import org.jgrapht.alg.interfaces.PartitioningAlgorithm.PartitioningImpl;

import br.com.pkmeanssimulator.model.Data;
import br.com.pkmeanssimulator.model.Element;
import br.com.pkmeanssimulator.model.Map;
import br.com.pkmeanssimulator.model.Metro;
import br.com.pkmeanssimulator.model.ModelFactory;
import br.com.pkmeanssimulator.model.SCSimulatorMatrix;
import br.com.pkmeanssimulator.model.SimulatorBuses;
import br.com.pkmeanssimulator.utils.algorithms.kmeans.KMeans;

public class PKMeansSimulator {

	private static SimulatorBuses simulatorBuses;
	private static Map map;
	private static Metro metro;
	private static SCSimulatorMatrix scSimulatorMatrix;
	private static KMeans kMeans;
	public static void main(String args[]) throws JAXBException {
		map = ModelFactory.buildMap();
		simulatorBuses = ModelFactory.buildSimulatorBuses();
		metro = ModelFactory.buildMetro();
		scSimulatorMatrix = ModelFactory.buildSCSimulatorMatrix();
		System.out.println(map.nodes.nodes.size());
		Data data = new Data();
		data.setMap(map);
		data.setMetro(metro);
		data.setScSimulatorMatrix(scSimulatorMatrix);
		data.setSimulatorBuses(simulatorBuses);
		kMeans = new KMeans(8, data);
		kMeans.calculate();
		kMeans.showClusters();
		
	}
	
}

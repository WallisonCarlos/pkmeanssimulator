package br.com.ppcsimulator;

import javax.xml.bind.JAXBException;

import org.jgrapht.alg.interfaces.PartitioningAlgorithm;
import org.jgrapht.alg.interfaces.PartitioningAlgorithm.PartitioningImpl;

import br.com.ppcsimulator.model.Data;
import br.com.ppcsimulator.model.Element;
import br.com.ppcsimulator.model.Map;
import br.com.ppcsimulator.model.Metro;
import br.com.ppcsimulator.model.ModelFactory;
import br.com.ppcsimulator.model.SCSimulatorMatrix;
import br.com.ppcsimulator.model.SimulatorBuses;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.BinaryGraphPartitioning;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.Graph;
import br.com.ppcsimulator.utils.algorithms.kmeans.KMeans;

public class App {

	private static SimulatorBuses simulatorBuses;
	private static Map map;
	private static Metro metro;
	private static SCSimulatorMatrix scSimulatorMatrix;
	private static KMeans kMeans;
	private static BinaryGraphPartitioning bgp;
	public static void main(String args[]) throws JAXBException, CloneNotSupportedException {
		ModelFactory.BASE_DIR = "scenarios/test";
		map = ModelFactory.buildMap();
//		simulatorBuses = ModelFactory.buildSimulatorBuses();
//		metro = ModelFactory.buildMetro();
//		scSimulatorMatrix = ModelFactory.buildSCSimulatorMatrix();
		System.out.println(map.nodes.nodes.size());
//		Data data = new Data();
//		data.setMap(map);
//		data.setMetro(metro);
//		data.setScSimulatorMatrix(scSimulatorMatrix);
//		data.setSimulatorBuses(simulatorBuses);
		bgp = new BinaryGraphPartitioning(8);
		bgp.partition(new Graph(map));
		bgp.printLeaves();
		
	}
	
}

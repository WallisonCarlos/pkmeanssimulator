package br.com.ppcsimulator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.xml.bind.JAXBException;

import br.com.ppcsimulator.model.Data;
import br.com.ppcsimulator.model.Map;
import br.com.ppcsimulator.model.Metro;
import br.com.ppcsimulator.model.ModelFactory;
import br.com.ppcsimulator.model.SCSimulatorMatrix;
import br.com.ppcsimulator.model.SimulatorBuses;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.BinaryGraphPartitioning;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.Graph;
import br.com.ppcsimulator.utils.algorithms.kmeans.KMeans;
import br.com.ppcsimulator.utils.file.FileManager;
import br.com.ppcsimulator.utils.runsimulator.DockerComposeUtils;
import br.com.ppcsimulator.utils.runsimulator.RunSimulator;

public class App {

	private static SimulatorBuses simulatorBuses;
	private static Map map;
	private static Metro metro;
	private static SCSimulatorMatrix scSimulatorMatrix;
	private static KMeans kMeans;
	private static BinaryGraphPartitioning bgp;
	public static void main(String args[]) throws JAXBException, CloneNotSupportedException, IOException {
		ModelFactory.BASE_DIR = "scenarios/base_scenario";
		map = ModelFactory.buildMap();
		simulatorBuses = ModelFactory.buildSimulatorBuses();
		metro = ModelFactory.buildMetro();
		scSimulatorMatrix = ModelFactory.buildSCSimulatorMatrix();
		Data data = new Data();
		data.setMap(map);
		data.setMetro(metro);
		data.setSimulatorBuses(simulatorBuses);
		data.setScSimulatorMatrix(scSimulatorMatrix);
		System.out.println(map.nodes.nodes.size());
		bgp = new BinaryGraphPartitioning(16);
		bgp.setData(data);
		Graph graph = new Graph(map);
		System.out.println(LocalDateTime.now());
		System.out.println("Before:\n"+graph);
		Graph g = bgp.coarsening(graph);
		System.out.println("After:\n"+g);
		System.out.println(LocalDateTime.now());
		bgp.partition(g);
		System.out.println(LocalDateTime.now());
		bgp.printLeaves();
		FileManager.create(new File("interscsimulator/docker-compose.yml"), DockerComposeUtils.content(8));
		Runnable run = new RunSimulator();
		run.run();
		System.out.println(LocalDateTime.now());
		
	}
	
}

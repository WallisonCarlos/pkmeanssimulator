package br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.ppcsimulator.model.Data;
import br.com.ppcsimulator.model.Link;
import br.com.ppcsimulator.model.Scenario;
import br.com.ppcsimulator.model.Trip;
import br.com.ppcsimulator.utils.file.FileManager;

public class Node {
	
	private int id;
	private Graph graph;
	private int rank;
	private Node left;
	private Node right;
	private double cutCost;
	private Scenario scenario = new Scenario();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Graph getGraph() {
		return graph;
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public double getCutCost() {
		return cutCost;
	}

	public void setCutCost(double cutCost) {
		this.cutCost = cutCost;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
	
	public void createPartition(Data data) {
//		for (int i = 0;i < data.getMetro().stations.stations.size();i++) {
//			Station station = data.getMetro().stations.stations.get(i);
//			if (graph.nodeExists(station.idNode)) {
//				scenario.getMetro().stations.stations.add(station);
//				for (int j = 0;j < data.getMetro().links.size();j++) {
//					LinkMetro l = data.getMetro().links.get(j);
//					if (station.idNode == l.idOrigin) {
//						scenario.getMetro().links.add(l);
//					}
//				}
//			}
//		}
		for (int i = 0;i < data.getMap().links.links.size();i++) {
			Link link = data.getMap().links.links.get(i);
			if (graph.nodeExists(link.from) || graph.nodeExists(link.to)) {
				scenario.getMap().links.links.add(link);
			}
		}
		for (br.com.ppcsimulator.model.Node node : graph.getVertices()) {
			scenario.getMap().nodes.nodes.add(node);
		}
		for (int i = 0;i < data.getScSimulatorMatrix().trips.size() ;i++) {
			Trip trip = data.getScSimulatorMatrix().trips.get(i);
			if (graph.nodeExists(trip.origin)) {
				scenario.getTrips().trips.add(trip);
			}
		}
//		TrafficSignals ts = new TrafficSignals();
//		ts.signals = data.getSignals();
//		scenario.setSignals(ts);
		try {
			String sc = "base_scenario_distributed-patition-"+rank;
			scenario.createFiles(sc);
			String configContent = "<scsimulator_config>\n" + 
					"  <config \n" + 
					"    trip_file=\"../"+sc+"/trips.xml\" \n" + 
					"    map_file=\"../"+sc+"/network.xml\" \n" + 
					"    output_file=\"../output/events.xml\" \n" + 
					"    traffic_signals_file=\"../"+sc+"/signals.xml\"\n" + 
					"    digital_rails_file=\"../"+sc+"/empty-digital-rails.xml\"\n" + 
					"    simulation_time=\"86400\"/>\n" + 
					"</scsimulator_config>";
			FileManager.create(new File("interscsimulator/mock-simulators/smart_city_model/"+sc+"/config.xml"), configContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

package br.com.ppcsimulator.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.ppcsimulator.utils.xml.XMLConversor;

public class Scenario {
	
	private Map map = new Map();
	private Metro metro = new Metro();
	private SCSimulatorMatrix trips = new SCSimulatorMatrix();
	private SimulatorBuses buses = new SimulatorBuses();
	private TrafficSignals signals = new TrafficSignals();
	
	public Scenario() {
		
	}
	
	public static Scenario build() {
		FactoryScenario factoryScenario = new FactoryScenario();
		return factoryScenario.buildScenario();
	}
	
	public void createFiles(String scenario) throws IOException {
		File dir = new File("output_scenarios/"+scenario+"/");
		if (dir.mkdir()) {
			new XMLConversor<Map>(map, new File("output_scenarios/"+scenario+"/map.xml")).createFile();
			new XMLConversor<SCSimulatorMatrix>(trips, new File("output_scenarios/"+scenario+"/trips.xml")).createFile();
			new XMLConversor<Metro>(metro, new File("output_scenarios/"+scenario+"/metro.xml")).createFile();
			new XMLConversor<TrafficSignals>(signals, new File("output_scenarios/"+scenario+"/signals.xml")).createFile();
			new XMLConversor<SimulatorBuses>(buses, new File("output_scenarios/"+scenario+"/buses.xml")).createFile();
		} else {
			System.err.println("Erro ao criar pasta do cenário!");
		}
	}
	
	private static class FactoryScenario {
		
		private Scenario scenario = new Scenario();
		
		public Scenario buildScenario() {
			createMap();
			createTrips();
			createSignals();
			return scenario;
		}
		
		public void createSignals() {
			for (int i = 0;i < 15;i++) {
				Nodes nodes = new Nodes();
				nodes.nodes.add(scenario.map.nodes.nodes.get(new Random().nextInt(scenario.map.nodes.nodes.size())));
				nodes.nodes.add(scenario.map.nodes.nodes.get(new Random().nextInt(scenario.map.nodes.nodes.size())));
				Phases phases = new Phases();
				phases.phases.add(new Phase(scenario.map.nodes.nodes.get(new Random().nextInt(scenario.map.nodes.nodes.size())).id, 45, 0));
				phases.phases.add(new Phase(scenario.map.nodes.nodes.get(new Random().nextInt(scenario.map.nodes.nodes.size())).id, 45, 0));
				phases.phases.add(new Phase(scenario.map.nodes.nodes.get(new Random().nextInt(scenario.map.nodes.nodes.size())).id, 28, 46));
				phases.phases.add(new Phase(nodes.nodes.get(0).id, 28, 46));
				scenario.signals.signals.add(new Signal(90, 9, nodes, phases));
			}
		}
		
		public void createTrips() {
			for (int i = 0;i < 50000;i++) {
				Link o = scenario.map.links.links.get(new Random().nextInt(scenario.map.links.links.size()));
				Node destination = scenario.map.nodes.nodes.get(new Random().nextInt(scenario.map.nodes.nodes.size()));
				Trip trip = new Trip("Wallison_Rocha_"+i, o.from, destination.id, o.id, 1, new Random().nextInt(500), "car", false);
				scenario.trips.trips.add(trip);
			}
		}
		
		public void createMap() {
			generateNodes();
			generateLinks();
		}
		
		private void generateNodes() {
			for (int i = 0;i < 32000;i++) {
				scenario.getMap()
				.nodes.nodes.add(
						new Node(
								i + 1,
								Math.random() * 50 * negativeOrPositive(),
								Math.random() * 50 * negativeOrPositive()
								)
						);
			}
		}
		
		private void generateLinks() {
			scenario.map.links.capPeriod = "01:00:00";
			scenario.map.links.effectiveCellSize = 7.5f;
			scenario.map.links.effectiveLaneWidth = 3.75f;
			for (int j = 0;j < 2;j++) {
				for (int i = 0;i < 32000;i++) {
					int neighbor = -1;
					int a = ((neighbor = ((int) Math.random() * scenario.map.nodes.nodes.size())) != i) ? neighbor : i + 1;
					scenario.map.
					links.links.add(
							new Link(
									(i + 1) * (j + 1),
									scenario.map.nodes.nodes.get(i).id,
									a,
									Math.random() * 100,
									Math.random() * 10,
									600,
									1,
									1, 
									"car",
									0,
									"residential"
									)
							);
				}
			}
		}
		
		private int negativeOrPositive() {
			int n = (int) (Math.random() * 50 + 1);
			return (n % 2 == 0) ? 1 : -1; 
		}
		
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Metro getMetro() {
		return metro;
	}

	public void setMetro(Metro metro) {
		this.metro = metro;
	}

	public SimulatorBuses getBuses() {
		return buses;
	}

	public void setBuses(SimulatorBuses buses) {
		this.buses = buses;
	}

	public TrafficSignals getSignals() {
		return signals;
	}

	public void setSignals(TrafficSignals signals) {
		this.signals = signals;
	}

	public SCSimulatorMatrix getTrips() {
		return trips;
	}

	public void setTrips(SCSimulatorMatrix trips) {
		this.trips = trips;
	}
	
	
}

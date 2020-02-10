package br.com.pkmeanssimulator.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import br.com.pkmeanssimulator.utils.xml.XMLConversor;

public class ModelFactory {
	
	public static Map buildMap() throws JAXBException {
		File mapFile = new File("scenarios/sp_completo/map.xml");
		System.out.println("Building Map");
		Map map = null;
		if (mapFile.exists()) {
			map = new XMLConversor<Map>(mapFile)
					.xmlToObject(JAXBContext.newInstance(Map.class));
			System.out.println("Complete Map");
		} else {
			System.err.println("Aquivo ou diretório: scenarios/sp_completo/map.xml. Não foi encontrado");
		}
		return map;
	}
	
	public static SimulatorBuses buildSimulatorBuses() throws JAXBException {
		File busFile = new File("scenarios/sp_completo/buses.xml");
		SimulatorBuses simulatorBuses = null;
		System.out.println("Building SimulatorBuses");
		if (busFile.exists()) {
			simulatorBuses = new XMLConversor<SimulatorBuses>(busFile)
					.xmlToObject(JAXBContext.newInstance(SimulatorBuses.class));
			System.out.println("Complete SimulatorBuses");
		} else {
			System.err.println("Aquivo ou diretório: scenarios/sp_completo/buses.xml. Não foi encontrado");
		}
		return simulatorBuses;
	}
	
	public static Metro buildMetro() throws JAXBException {
		File file = new File("scenarios/sp_completo/metro.xml");
		Metro metro = null;
		System.out.println("Building Metro");
		if (file.exists()) {
			metro = new XMLConversor<Metro>(file)
					.xmlToObject(JAXBContext.newInstance(Metro.class));
			System.out.println("Complete Metro");
		} else {
			System.err.println("Aquivo ou diretório: scenarios/sp_completo/metro.xml. Não foi encontrado");
		}
		return metro;
	}
	
	public static SCSimulatorMatrix buildSCSimulatorMatrix() throws JAXBException {
		File file = new File("scenarios/sp_completo/trips.xml");
		SCSimulatorMatrix scSimulatorMatrix = null;
		System.out.println("Building SCSimulatorMatrix");
		if (file.exists()) {
			scSimulatorMatrix = new XMLConversor<SCSimulatorMatrix>(file)
					.xmlToObject(JAXBContext.newInstance(SCSimulatorMatrix.class));
			System.out.println("Complete SCSimulatorMatrix");
		} else {
			System.err.println("Aquivo ou diretório: scenarios/sp_completo/trips.xml. Não foi encontrado");
		}
		return scSimulatorMatrix;
	}

}

package br.com.pkmeanssimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private List<Trip> trips = new ArrayList<>();
	private List<Signal> signals = new ArrayList<>();
	private Map map;
	private Metro metro;
	private List<Bus> buses = new ArrayList<>();
	
	public List<Trip> getTrips() {
		return trips;
	}
	
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	
	public List<Signal> getSignals() {
		return signals;
	}
	
	public void setSignals(List<Signal> signals) {
		this.signals = signals;
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
	
	public List<Bus> getBuses() {
		return buses;
	}
	
	public void setBuses(List<Bus> buses) {
		this.buses = buses;
	}
	
	

}

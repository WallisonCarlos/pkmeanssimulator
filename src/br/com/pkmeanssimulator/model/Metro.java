package br.com.pkmeanssimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Metro {
	
	private List<Station> stations = new ArrayList<>();
	private List<LinkMetro> links = new ArrayList<>();
	
	public List<Station> getStations() {
		return stations;
	}
	
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	public List<LinkMetro> getLinks() {
		return links;
	}
	
	public void setLinks(List<LinkMetro> links) {
		this.links = links;
	}
	
	

}

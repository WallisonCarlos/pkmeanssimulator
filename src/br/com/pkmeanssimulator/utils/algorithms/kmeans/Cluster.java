package br.com.pkmeanssimulator.utils.algorithms.kmeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.pkmeanssimulator.model.Element;
import br.com.pkmeanssimulator.model.Node;

public class Cluster {
	
	private int id;
	private Node centroid;
	private List<Element> elements = new ArrayList<>();
	
	public Cluster() {
		
	}
	
	public Cluster(int id) {
		this.id = id;
	}
	
	public void clear() {
		elements.clear();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getCentroid() {
		return centroid;
	}

	public void setCentroid(Node centroid) {
		this.centroid = centroid;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	public int numberElements() {
		return elements.size();
	}
	
	@Override
	public String toString() {
		return "{\"id\": "+id+", \"centroid\": "+centroid+", \"numberElements\": "+numberElements()+"}";
	}

}

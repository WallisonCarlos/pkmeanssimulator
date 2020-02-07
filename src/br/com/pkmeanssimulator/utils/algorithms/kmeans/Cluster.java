package br.com.pkmeanssimulator.utils.algorithms.kmeans;

import java.util.ArrayList;
import java.util.List;

import br.com.pkmeanssimulator.model.Element;
import br.com.pkmeanssimulator.model.Node;

public class Cluster {
	
	
	private Node centroid;
	private List<Element> elements = new ArrayList<>();
	
	public void clear() {
		elements.clear();
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
	
	

}
